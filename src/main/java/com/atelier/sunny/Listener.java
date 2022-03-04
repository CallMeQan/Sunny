package com.atelier.sunny;

import com.atelier.sunny.manager.CustomMessage;
import com.atelier.sunny.manager.command.CommandManager;
import com.atelier.sunny.manager.command.SlashCommandManager;
import com.atelier.sunny.manager.event.EventManager;
import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.models.MongoDBHandler;
import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    private static final SlashCommandManager manager = new SlashCommandManager();
    private static final CommandManager cmdManager = new CommandManager();
    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);
        Guild guild = event.getGuild();
        GuildDocument guildDocument = new GuildDocument()
                .setGuildId(guild.getId())
                .setGuildName(guild.getName())
                .setChannelID("0");
        DatabaseUtils.createDocument(DatabaseUtils.CollName.GUILD, guildDocument.toDoc());
        guild.updateCommands().addCommands(SlashCommandManager.COMMAND_DATA_LIST).queue();
        logger.info("Bot join " + event.getGuild().getName());

        EventManager.addTimer(new ServerSchedule(guildDocument));
        logger.info("Timer added "+guildDocument.getGuildName());
        EventManager.startTimer(guildDocument.getGuildID());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        super.onGuildLeave(event);
        DatabaseUtils.deleteDocument("guildID", event.getGuild().getId(), DatabaseUtils.CollName.GUILD);
        logger.info("Bot leave " + event.getGuild().getName());
    }

    private void setupTimer() {
        DatabaseUtils.getDocuments(DatabaseUtils.CollName.GUILD).forEach(document -> {
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
            Document doc = DatabaseUtils.getDocument("guildID", guild.getId(), DatabaseUtils.CollName.GUILD);
            if(doc == null) {
                GuildDocument guildDocument = new GuildDocument()
                        .setGuildId(guild.getId())
                        .setGuildName(guild.getName())
                        .setChannelID("0");
                DatabaseUtils.createDocument(DatabaseUtils.CollName.GUILD, guildDocument.toDoc());
            }
        });

        for (Guild guild : event.getJDA().getGuilds())
            guild.updateCommands().addCommands(SlashCommandManager.COMMAND_DATA_LIST).queue();
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

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        // if(event.getMessage().getContentDisplay().startsWith(System.getenv("DISCORD_PREFIX")))
            //cmdManager.process(event);
        CustomMessage.process(event); // Atelier Only
    }
}
