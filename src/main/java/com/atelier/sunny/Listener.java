package com.atelier.sunny;

import com.atelier.sunny.manager.command.CommandManager;
import com.atelier.sunny.manager.event.EventManager;
import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.models.MongoDBHandler;
import com.atelier.sunny.utils.DatabaseUtils;
import com.atelier.sunny.utils.GuildUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Listener extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();
    private final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);
        Guild guild = event.getGuild();
        GuildDocument guildDocument = new GuildDocument()
                .setGuildId(guild.getId())
                .setGuildName(guild.getName())
                .setChannelID(GuildUtils.getFirstTextChannel(guild).getId());
        DatabaseUtils.createDocument(guildDocument.toDoc());
        logger.info("Bot join " + event.getGuild().getName());

        EventManager.addTimer(new ServerSchedule(guildDocument));
        logger.info("Timer added "+guildDocument.getGuildName());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        super.onGuildLeave(event);
        DatabaseUtils.deleteDocument("guildID", event.getGuild().getId());
        logger.info("Bot leave " + event.getGuild().getName());
    }

    private void setupTimer() {
        DatabaseUtils.getDocuments().forEach(document -> {
            GuildDocument guildDocument = GuildDocument.convertDocument(document);
            EventManager.addTimer(new ServerSchedule(guildDocument));
            logger.info("Timer added "+guildDocument.getGuildName());
        });
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        // Everytime this bot started up, it will go straight around every guild that it joined
        // If not exist in db, then insert one
        event.getJDA().getGuilds().forEach(guild -> {
            Document doc = DatabaseUtils.getDocument("guildID", guild.getId());
            if(doc == null) {
                GuildDocument guildDocument = new GuildDocument()
                        .setGuildId(guild.getId())
                        .setGuildName(guild.getName())
                        .setChannelID(GuildUtils.getFirstTextChannel(guild).getId());
                DatabaseUtils.createDocument(guildDocument.toDoc());
            }
        });

        for (Guild guild : event.getJDA().getGuilds())
            guild.updateCommands().addCommands(CommandManager.COMMAND_DATA_LIST).queue();
        logger.info(event.getJDA().getSelfUser().getAsTag() + " is ready");

        setupTimer();
        EventManager.startAllTimer();
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        super.onShutdown(event);
        MongoDBHandler.getClient().close();
        logger.warn("Client disconnected on " + event.getTimeShutdown());
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        super.onSlashCommand(event);
        manager.process(event);
    }
}
