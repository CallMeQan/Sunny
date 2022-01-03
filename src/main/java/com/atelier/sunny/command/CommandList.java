package com.atelier.sunny.command;

import com.atelier.sunny.commands.SetCommand;
import com.atelier.sunny.models.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.TreeMap;

// Ahh yes, i copied from another source :))
public class CommandList {
    private final SetCommand setCommand = new SetCommand();

    private static Logger logger = LogManager.getLogger(CommandList.class);
    private final TreeMap<String, Command> cmds = new TreeMap<>((o1, o2) -> o1.compareToIgnoreCase(o2));
    public CommandList(){
        try
        {
            for(Field field : CommandList.class.getDeclaredFields())
            {
                if(!field.getName().endsWith("Command"))
                    continue;

                Command cmd = (Command)field.get(this);
                cmds.put(cmd.getName(), cmd);
            }

        }catch(Exception e)
        {
            logger.error(e.getMessage(), e.getCause());
        }
    }

    @Nullable
    public Command getCommandByName(String name){ return cmds.getOrDefault(name, null); };
}
