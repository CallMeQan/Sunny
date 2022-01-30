package com.atelier.sunny.commands.sub;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class ImageSetCommand {
    public void run(SlashCommandEvent event) {
        event.getOption("img").getAsString();
    }
}
