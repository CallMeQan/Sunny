package com.atelier.sunny.commands;

import com.atelier.sunny.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SetCommand extends Command {
    public SetCommand() {
        super("set", "Setting your server config", new String[]{"key", "value"}, Permission.ADMINISTRATOR);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {

    }

    @Override
    public void printHelp(MessageReceivedEvent event) {

    }
}
