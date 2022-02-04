package com.atelier.sunny.commands.sub;

import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.utils.DatabaseUtils;
import com.atelier.sunny.utils.URLUtils;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class TextSetCommand {
    private final Logger logger = LoggerFactory.getLogger(ChannelSetCommand.class);

    public void run(SlashCommandEvent event) {
        MessageFormat response = new MessageFormat("Succeed changed {0} text to {1}");

        for (OptionMapping optionMapping: event.getOptions()) {
            String name = optionMapping.getName();
            String value = optionMapping.getAsString();
            switch (name) {
                case "morning" -> GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId()))
                        .setText(0, value)
                        .update();
                case "afternoon" -> GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId()))
                        .setText(1, value)
                        .update();
                case "night" -> GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId()))
                        .setText(2, value)
                        .update();
                default -> {
                    logger.info("\"" + event.getGuild().getName() + "\" had ran an unknown option");
                    event.reply("Unknown " + name).queue();
                    return;
                }
            }
            logger.info("\""+event.getGuild().getName()+"\" had changed "+name+" text to "+value);
            event.reply(response.format(new String[]{name, value})).queue();
            return;
        }
        event.reply("Invalid parameters for command, /help set for more information").complete();
    }
}
