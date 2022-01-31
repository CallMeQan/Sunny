package com.atelier.sunny.manager.command;

import com.atelier.sunny.Bot;
import com.atelier.sunny.commands.SetCommand;
import com.atelier.sunny.commands.SlapCommand;
import com.atelier.sunny.commands.TestCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.ArrayList;

public class CommandManager {
    private static final ArrayList<Command> COMMAND_LIST = new ArrayList<>();
    public static final ArrayList<CommandData> COMMAND_DATA_LIST = new ArrayList<>();

    public CommandManager() {
        addCommand(new SetCommand());
        addCommand(new TestCommand());
        // addCommand(new SlapCommand());
    }

    private void addCommand(Command cmd) {
        COMMAND_LIST.add(cmd);
        COMMAND_DATA_LIST.add(cmd.data);
    }

    public Command getCommand(String query) {
        for (Command cmd : COMMAND_LIST) {
            if (cmd.data.getName().equalsIgnoreCase(query)) return cmd;
        }
        return null;
    }

    public void process(SlashCommandEvent event) {
        Command cmd = this.getCommand(event.getName());
        if (cmd != null) {
            if (!PermissionUtil.checkPermission(event.getMember(), cmd.perms.toArray(Permission[]::new))) return;
            cmd.run(event);
        }
    }
}
