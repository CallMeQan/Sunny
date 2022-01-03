package com.atelier.sunny.commands;

import com.atelier.sunny.Storage;
import com.atelier.sunny.models.Command;
import com.atelier.sunny.models.StorageModel;
import com.atelier.sunny.utils.MentionUtil;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class SetCommand extends Command {
    private final Logger logger = LogManager.getLogger(SetCommand.class);
    public SetCommand() {
        super("set", "Setting your server config", new String[]{"key", "value"}, Permission.ADMINISTRATOR);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        MessageAction respond;
        String key = args[0];
        String value = args[1];
        Guild guild = event.getGuild();
        StorageModel.GuildModel guildModel = Storage.json.getGuildById(guild.getId());
        if (guildModel == null){
            guildModel = Storage.createDefaultGuildModel(guild);
            Storage.json.addGuild(guildModel);

        }
        switch (key){
            case "channel":
                if (MentionUtil.isChannelMention(value))
                    guildModel.setChannelId(value);
                respond = event.getChannel().sendMessage("Success changed channel id!");
                break;
            case "prefix":
                if (value.trim() != "")
                    guildModel.setPrefix(value);
                respond = event.getChannel().sendMessage("Success changed prefix!");
                break;
            default:
                respond = event.getChannel().sendMessage("Unknown key name `"+key+"`");
                Storage.save();
        }
        respond.complete();

    }

    @Override
    public void printHelp(MessageReceivedEvent event) {

    }
}
