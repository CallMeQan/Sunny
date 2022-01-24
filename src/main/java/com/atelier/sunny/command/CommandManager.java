package com.atelier.sunny.command;

import com.atelier.sunny.Bot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class CommandManager {
    private final CommandList cmdList = new CommandList();
    private String PREFIX = Bot.PREFIX;
    private JDA jda;
    private Logger logger = LoggerFactory.getLogger(CommandManager.class);

    public void process(JDA jda, MessageReceivedEvent event) {
        this.jda = jda;
        String[] messageRaw = event.getMessage().getContentRaw().split(" ");
        try{
            String cmdName = messageRaw[0].substring(PREFIX.length());
            String[] args = Arrays.copyOfRange(messageRaw, 1, messageRaw.length);
            Command cmd = parseCmd(cmdName);

            if(PermissionUtil.checkPermission(event.getMember(), cmd.getPermission()))
                cmd.run(event, args);
        }catch (CmdNotFoundException e){
            logger.error(e.getMessage(), e.getCause());
        }
    }

    private Command parseCmd(String cmdName) throws CmdNotFoundException {
        Command cmd = cmdList.getCommandByName(cmdName);
        if (cmd==null) throw new CmdNotFoundException(cmdName);
        return cmd;
    }

    private static class CmdNotFoundException extends Exception
    {
        public CmdNotFoundException(String input)
        {
            super("Command not found '"+input+"'");
        }
    }
}
