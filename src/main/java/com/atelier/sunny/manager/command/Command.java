package com.atelier.sunny.manager.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;

public abstract class Command {
    public CommandData data;
    public List<Permission> perms = List.of();

    public abstract void run(SlashCommandEvent event);
}
