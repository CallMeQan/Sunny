package com.atelier.sunny.manager.command;

import com.atelier.sunny.Bot;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static final ArrayList<Command> COMMAND_LIST = new ArrayList<>();
    private static final String PREFIX = Bot.PREFIX;

    public CommandManager() {

    }

    private void addCommand(Command... cmd) {
        COMMAND_LIST.addAll(List.of(cmd));
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
