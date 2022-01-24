package com.atelier.sunny.commands;

import com.atelier.sunny.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(GetCommand.class);

    public GetCommand() {
        super("get", "Get variable from program, owner bot only", new String[]{"variable"}, Permission.ADMINISTRATOR);
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        if (event.getAuthor().getId() != "603460160307855371") return;

    }

    @Override
    public void printHelp(MessageReceivedEvent event) {

    }
}
