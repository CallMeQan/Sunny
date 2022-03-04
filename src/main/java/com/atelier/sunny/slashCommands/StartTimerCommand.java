package com.atelier.sunny.slashCommands;

import com.atelier.sunny.manager.command.SlashCommand;
import com.atelier.sunny.manager.event.EventManager;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class StartTimerCommand extends SlashCommand {
    private final Logger logger = LoggerFactory.getLogger(StartTimerCommand.class);

    public StartTimerCommand(){
        super(new CommandData("owner_start_timer", "BOT OWNER ONLY, Start a specific server thread")
                .addOption(OptionType.STRING, "id", "ID server", true),
                null);
    }

    @Override
    public void run(SlashCommandEvent event) {
        if (!event.getUser().getId().equals("603460160307855371")) {
            event.reply("You are not my owner, so stfu").setEphemeral(true).queue();
            return;
        }

        String id = Objects.requireNonNull(event.getOption("id")).getAsString();
        if (!StringUtils.isNumeric(id)) {
            event.reply("Pls provided me number id of that server").setEphemeral(true).queue();
            return;
        }

        if (EventManager.isTimerInList(id)) {
            event.reply("Thread already started!").setEphemeral(true).queue();
            return;
        }

        if (event.getJDA().getGuildById(id) == null){
            event.reply("Invalid server ID").setEphemeral(true).queue();
            return;
        }

        EventManager.startTimer(id);
        event.reply("Started \""+event.getJDA().getGuildById(id).getName()+"\"'s thread").setEphemeral(true).queue();
        logger.info("\""+id+"\" had started thread");
    }
}
