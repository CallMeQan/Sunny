package com.atelier.sunny.models;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public abstract class Command {
    private final String name;
    private final String description;
    private final String[] syntax;
    private final Permission permission;
    public Command(String name, String description, String[] syntax, Permission permission){
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.syntax = syntax;
        this.permission = permission;
    }

    public abstract void run(MessageReceivedEvent event, String[] args);
    public abstract void printHelp(MessageReceivedEvent event);

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String[] getSyntax(){
        return syntax;
    }

    public Permission getPermission() {
        return this.permission;
    }
}
