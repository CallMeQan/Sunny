package com.atelier.sunny.events;

import com.atelier.sunny.Bot;
import com.atelier.sunny.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class Message extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String PREFIX = Bot.PREFIX;
        if (event.getMessage().getContentDisplay().startsWith(PREFIX)) manager.process(event.getJDA(), event);
    }
}
