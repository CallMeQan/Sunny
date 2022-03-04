package com.atelier.sunny.manager.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Objects;

public abstract class Command {
    public final String[] args;
    public final String name;
    public final List<Permission> perms;
    public final String description;
    public final String[] aliases;

    public Command(String name, String description, String[] args, List<Permission> perms, String[] aliases) {
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.perms = perms;
        this.args = args;
        this.aliases = aliases;
    }
    public abstract void run(MessageReceivedEvent event, String[] args);
}
