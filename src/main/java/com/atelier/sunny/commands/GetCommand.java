package com.atelier.sunny.commands;

import com.atelier.sunny.manager.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;

public class GetCommand extends Command {
    public GetCommand() {
        super();
        this.data = new CommandData("get", "Get variable from program, owner bot only")
                .addOption(OptionType.STRING, "variable", "Variable to get", true);
        this.perms = List.of(Permission.ADMINISTRATOR);
    }

    @Override
    public void run(SlashCommandEvent event) {

    }
}
