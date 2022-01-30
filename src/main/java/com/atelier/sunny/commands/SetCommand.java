package com.atelier.sunny.commands;

import com.atelier.sunny.commands.sub.ChannelSetCommand;
import com.atelier.sunny.commands.sub.ImageSetCommand;
import com.atelier.sunny.manager.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.List;

public class SetCommand extends Command {
    public SetCommand() {
        super();
        this.data = new CommandData("set", "Setting your server config")
                .addSubcommands(
                        new SubcommandData("channel", "Set the channel")
                                .addOption(OptionType.CHANNEL, "channel", "Channel to send msg", true),
                        new SubcommandData("image", "Set the Image")
                                .addOption(OptionType.STRING, "img", "Image Url", true)
                );
        this.perms = List.of(Permission.ADMINISTRATOR);
    }

    @Override
    public void run(SlashCommandEvent event) {
        if (event.getSubcommandName().equals("channel")) {
            new ChannelSetCommand().run(event);
            return;
        }
        if (event.getSubcommandName().equals("image")) {
            new ImageSetCommand().run(event);
            return;
        }
        /*
        if (args.length < 1) return;
        if (Objects.equals(args[0], "channel") && args.length > 1 && MentionUtil.isChannelMention(args[1])) {
            doc.setChannelID(MentionUtil.getId(args[1])).update();
            channel.sendMessage("Succeed updated channel to "+args[1]).complete();
            logger.info(doc.getGuildName() + " has changed their channel to "+args[1]);
            return;
        }else if (Objects.equals(args[0], "image") && args.length > 1){
            List<Message.Attachment> attachments = event.getInteraction().
            if (attachments.size() != 1) {channel.sendMessage("Sorry we only take 1 image").complete(); return;}
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
        }*/
    }
}
