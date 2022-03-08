package com.atelier.sunny.manager.command;

import com.atelier.sunny.commands.OsuLinkAccountCommand;
import com.atelier.sunny.commands.RecentCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

// Currently, normal commands are disabled due to OsuClient
public class CommandManager {
    private static final ArrayList<Command> COMMANDS = new ArrayList<>();
    public CommandManager(){
        // addCommand(new OsuLinkAccountCommand());
        // addCommand(new RecentCommand());
    }

    private void addCommand(Command cmd) {
        COMMANDS.add(cmd);
    }

    private Command getCommand(String name) {
        for (Command cmd : COMMANDS) {
            if (cmd.name.equalsIgnoreCase(name) ||
                    Arrays.asList(cmd.aliases).contains(name)) return cmd;
        }
        return null;
    }

    public void process(MessageReceivedEvent event){
//        String message = event.getMessage().getContentDisplay();
//        String args[] = message.split(System.getenv("DISCORD_PREFIX"))[1].strip().split(" ");
//        String cmdName = args[0];
//        Command cmd = getCommand(cmdName);
//        if (cmd != null) {
//            if (cmd.perms != null && !PermissionUtil.checkPermission(event.getMember(), cmd.perms.toArray(Permission[]::new)))
//                event.getMessage().reply("You don't have permission[s] to invoke this command").queue();
//            else if (cmd.args.length > args.length)
//                event.getMessage().reply("Missing argument to invoke command").queue();
//            cmd.run(event, Arrays.copyOfRange(args, 1, args.length));
//        }
    }
}
