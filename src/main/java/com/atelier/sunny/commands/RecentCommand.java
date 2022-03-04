package com.atelier.sunny.commands;

import com.atelier.sunny.Bot;
import com.atelier.sunny.manager.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RecentCommand extends Command {
    public RecentCommand() {
        super("recent", "Show your latest play", null, null, new String[]{"rs", "recent"});
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        
    }
}
