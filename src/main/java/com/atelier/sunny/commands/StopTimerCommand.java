package com.atelier.sunny.commands;

import com.atelier.sunny.commands.sub.ImageSetCommand;
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

public class StopTimerCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(StopTimerCommand.class);

    public StopTimerCommand(){
        super();
        this.data = new CommandData("owner_stop_timer", "BOT OWNER ONLY, Stop a specific server thread")
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

        if (!EventManager.isTimerInList(id)) {
            event.reply("Thread doesn't exist").queue();
            return;
        }

        if (event.getJDA().getGuildById(id) == null){
            event.reply("Invalid server ID").queue();
            return;
        }

        EventManager.stopTimer(id);
        event.reply("Stopped \""+event.getJDA().getGuildById(id).getName()+"\"'s thread").queue();
        logger.info("\""+id+"\" had stopped thread");
    }
}
