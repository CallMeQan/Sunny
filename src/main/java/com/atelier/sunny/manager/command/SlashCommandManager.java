package com.atelier.sunny.manager.command;

import com.atelier.sunny.slashCommands.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.ArrayList;

public class SlashCommandManager {
    private static final ArrayList<SlashCommand> COMMAND_LIST = new ArrayList<>();
    public static final ArrayList<CommandData> COMMAND_DATA_LIST = new ArrayList<>();

    public SlashCommandManager() {
        addCommand(new SetCommand());
        addCommand(new TestCommand());
        addCommand(new SlapCommand());
        addCommand(new MigrateCommand());
        // addCommand(new StartTimerCommand());
        // addCommand(new StopTimerCommand());
    }

    private void addCommand(SlashCommand cmd) {
        COMMAND_LIST.add(cmd);
        COMMAND_DATA_LIST.add(cmd.data);
    }

    public SlashCommand getCommand(String query) {
        for (SlashCommand cmd : COMMAND_LIST) {
            if (cmd.data.getName().equalsIgnoreCase(query)) return cmd;
        }
        return null;
    }

    public void process(SlashCommandEvent event) {
        SlashCommand cmd = this.getCommand(event.getName());
        if (cmd != null) {
            if (cmd.perms == null) return;
            else if (!PermissionUtil.checkPermission(event.getMember(), cmd.perms.toArray(Permission[]::new))) return;
            cmd.run(event);
        }
    }
}
