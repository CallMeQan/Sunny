package com.atelier.sunny.commands;

import com.atelier.sunny.manager.command.Command;
import com.atelier.sunny.manager.event.EventManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class StartTimerCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(StartTimerCommand.class);

    public StartTimerCommand(){
        super();
        this.data = new CommandData("owner_start_timer", "BOT OWNER ONLY, Start a specific server thread")
                .addOption(OptionType.STRING, "id", "ID server", true);
        this.perms = List.of(Permission.ADMINISTRATOR);
    }

    @Override
    public void run(SlashCommandEvent event) {
        if (!event.getUser().getId().equals("603460160307855371")) {
            event.reply("You are not my owner, so stfu").queue();
            return;
        }

        String id = Objects.requireNonNull(event.getOption("id")).getAsString();
        if (!StringUtils.isNumeric(id)) {
            event.reply("Pls provided me number id of that server").queue();
            return;
        }

        if (EventManager.isTimerInList(id)) {
            event.reply("Thread already started!").queue();
            return;
        }

        if (event.getJDA().getGuildById(id) == null){
            event.reply("Invalid server ID").queue();
            return;
        }

        EventManager.startTimer(id);
        event.reply("Started \""+event.getJDA().getGuildById(id).getName()+"\"'s thread").queue();
        logger.info("\""+id+"\" had started thread");
    }
}
