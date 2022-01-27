package com.atelier.sunny.commands;

import com.atelier.sunny.Storage;
import com.atelier.sunny.command.Command;
import com.atelier.sunny.utils.MentionUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SetCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(SetCommand.class);
    public SetCommand() {
        super("set", "Setting your server config", new String[]{"key", "value"}, Permission.ADMINISTRATOR);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        if (args.length < 2) return;
        if (args[0] == "channel" && MentionUtil.isChannelMention(args[1])) {
            Storage.BasicInformation information = Storage.INSTANCE.getGuildById(event.getGuild().getId())
                    .setChannelId(args[1]);
            Storage.INSTANCE.updateGuild(information);
            event.getChannel().sendMessage("Set channel to "+ args[1]).complete();
            logger.info("Update channelId to "+args[1] +" in server '"+ event.getGuild().getId());
        }
    }

    @Override
    public void printHelp(MessageReceivedEvent event) {

    }
}
