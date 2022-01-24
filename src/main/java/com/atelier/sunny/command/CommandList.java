package com.atelier.sunny.command;

import com.atelier.sunny.commands.GetCommand;
import com.atelier.sunny.commands.SetCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.TreeMap;

// Ahh yes, i copied from another source :))
// Don't ask me where, even i don't remember it at all :D
public class CommandList {
    private final SetCommand setCommand = new SetCommand();
    private final GetCommand getCommand = new GetCommand();

    private static Logger logger = LoggerFactory.getLogger(CommandList.class);
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
    public Command getCommandByName(String name){ return cmds.getOrDefault(name, null); }
}
