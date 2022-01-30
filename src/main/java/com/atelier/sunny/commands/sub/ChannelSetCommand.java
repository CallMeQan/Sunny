package com.atelier.sunny.commands.sub;

import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class ChannelSetCommand {
    public void run(SlashCommandEvent event) {
        GuildChannel channel = event.getOption("channel").getAsGuildChannel();
        GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId()))
                .setChannelID(channel.getId())
                .update();
        event.reply(channel.getId()).queue();
    }
}
