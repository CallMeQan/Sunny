package com.atelier.sunny.manager.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;
import java.util.Objects;

public abstract class SlashCommand {
    public final CommandData data;
    public final List<Permission> perms;

    public SlashCommand(CommandData data, List<Permission> perms){
        this.data = Objects.requireNonNull(data);
        this.perms = Objects.requireNonNullElse(perms, List.of());
    }

    public abstract void run(SlashCommandEvent event);
}
