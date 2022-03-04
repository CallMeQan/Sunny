package com.atelier.sunny.slashCommands;

import com.atelier.sunny.manager.command.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MigrateGetAsFileCommand extends SlashCommand {
    private String pathToFile = "migrate.txt";
    private Logger logger = LoggerFactory.getLogger(MigrateGetAsFileCommand.class);

    public MigrateGetAsFileCommand() {
        super(new CommandData("migrate_get", "Get file of migrate"), List.of(Permission.ADMINISTRATOR));
    }

    @Override
    public void run(SlashCommandEvent event) {
        File file = new File(pathToFile);
        try {
            file.createNewFile();
            event.reply("Here")
                    .addFile(file, "migrate.txt")
                    .queue();

        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            event.reply("IOException cause error, please check the log!").queue();
        }
    }
}
