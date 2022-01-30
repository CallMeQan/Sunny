package com.atelier.sunny.commands;

import com.atelier.sunny.command.Command;
import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.utils.DatabaseUtils;
import com.atelier.sunny.utils.MentionUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;


public class SetCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(SetCommand.class);
    public SetCommand() {
        super("set", "Setting your server config", new String[]{"key", "value"}, Permission.ADMINISTRATOR);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        GuildDocument doc = GuildDocument.convertDocument(DatabaseUtils.getDocument("guildID", event.getGuild().getId()));
        MessageChannel channel = event.getChannel();

        if (args.length < 1) return;
        if (Objects.equals(args[0], "channel") && args.length > 1 && MentionUtil.isChannelMention(args[1])) {
            doc.setChannelID(MentionUtil.getId(args[1])).update();
            channel.sendMessage("Succeed updated channel to "+args[1]).complete();
            logger.info(doc.getGuildName() + " has changed their channel to "+args[1]);
            return;
        }else if (Objects.equals(args[0], "image") && args.length > 1){
            List<Message.Attachment> attachments = event.getMessage().getAttachments();
            if (attachments.size() < 1 || attachments.size() >= 2) {channel.sendMessage("Sorry we only take 1 image").complete(); return;}
            Message.Attachment a = attachments.get(0);

            if (!a.isImage()) {channel.sendMessage("Invalid input").complete(); return;}

            switch (args[1].toLowerCase()) {
                case "morning":
                    doc.setImageUrl(0, a.getUrl()).update();
                    channel.sendMessage("Updated image url for morning to "+a.getUrl()).complete();
                    logger.info(doc.getGuildName() + " had changed image morning to "+a.getUrl());
                    return;
                case "afternoon":
                    doc.setImageUrl(1, a.getUrl()).update();
                    channel.sendMessage("Updated image url for afternoon to "+a.getUrl()).complete();
                    logger.info(doc.getGuildName() + " had changed image afternoon to "+a.getUrl());
                    return;
                case "evening":
                    doc.setImageUrl(2, a.getUrl()).update();
                    channel.sendMessage("Updated image url for evening to "+a.getUrl()).complete();
                    logger.info(doc.getGuildName() + " had changed image evening to "+a.getUrl());
                    return;
                default:
                    channel.sendMessage("Invalid provided argument like `morning`, `afternoon` or `evening`");
                    return;
            }
        }
    }

    @Override
    public void printHelp(MessageReceivedEvent event) {

    }
}
