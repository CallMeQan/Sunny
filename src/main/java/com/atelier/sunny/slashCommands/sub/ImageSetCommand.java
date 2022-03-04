package com.atelier.sunny.slashCommands.sub;

import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.utils.DatabaseUtils;
import com.atelier.sunny.utils.URLUtils;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class ImageSetCommand {
    private final Logger logger = LoggerFactory.getLogger(ImageSetCommand.class);

    public void run(SlashCommandEvent event) {
        MessageFormat response = new MessageFormat("Succeed changed image for {0} to {1}");
        GuildDocument document = GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId(), DatabaseUtils.CollName.GUILD));

        for (OptionMapping optionMapping: event.getOptions()) {
            String name = optionMapping.getName();
            String value = optionMapping.getAsString();
            if (URLUtils.isValid(value)){

                switch (name) {
                    case "morning" -> document.setImageUrl(0, value).update();
                    case "afternoon" -> document.setImageUrl(1, value).update();
                    case "night" -> document.setImageUrl(2, value).update();
                    default -> {
                        logger.info("\"" + event.getGuild().getName() + "\" had ran an unknown option: "+name);
                        event.reply("Unknown " + name).queue();
                        return;
                    }
                }
                logger.info("\""+event.getGuild().getName()+"\" had changed "+name+" image to "+value);
                event.reply(response.format(new String[]{name, value})).queue();
                return;
            }
        }
        event.reply("Invalid parameters for command, /help set for more information").complete();
    }
}
