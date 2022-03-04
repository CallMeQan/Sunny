package com.atelier.sunny.slashCommands.sub;

import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelSetCommand {
    private final Logger logger = LoggerFactory.getLogger(ChannelSetCommand.class);

    public void run(SlashCommandEvent event) {
        GuildChannel channel = event.getOption("channel").getAsGuildChannel();
        GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId(), DatabaseUtils.CollName.GUILD))
                .setChannelID(channel.getId())
                .update();
        event.reply("Succeed changed channel to " + channel.getAsMention()).queue();
        logger.info("\""+event.getGuild().getName()+"\" had changed channel id to " + channel.getId());
    }
}
