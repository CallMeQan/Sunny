package com.atelier.sunny.slashCommands;

import com.atelier.sunny.manager.command.SlashCommand;
import com.atelier.sunny.models.DefaultEmbed;
import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.bson.Document;

import java.util.List;

public class TestCommand extends SlashCommand {
    public TestCommand(){
        super(new CommandData("test", "Test all embed"), List.of(Permission.MANAGE_SERVER));
    }
    @Override
    public void run(SlashCommandEvent event) {
        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();
        Document document = DatabaseUtils.getDocument("guildID", guild.getId(), DatabaseUtils.CollName.GUILD);
        channel.sendMessageEmbeds(DefaultEmbed.MORNING(document)).queue();
        channel.sendMessageEmbeds(DefaultEmbed.AFTERNOON(document)).queue();
        channel.sendMessageEmbeds(DefaultEmbed.NIGHT(document)).queue();
        event.reply("Finished").complete();
    }
}
