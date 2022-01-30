package com.atelier.sunny.commands.sub;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class ChannelSetCommand {
    public void run(SlashCommandEvent event) {
        event.getOption("channel").getAsGuildChannel();
    }
}
