package com.atelier.sunny.slashCommands;

import com.atelier.sunny.manager.command.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class MigrateCommand extends SlashCommand {
    private String pathToFile = "migrate.txt";
    private Logger logger = LoggerFactory.getLogger(MigrateCommand.class);

    public MigrateCommand(){
        super(new CommandData("migrate", "Migrate, you only can set one! And only ONE!")
                .addOption(OptionType.STRING, "username", "Your in-game username, WARNING THIS ISN'T A JOKE", true)
                .addOption(OptionType.STRING, "password", "Your password of your in-game account", true),
                List.of(Permission.MESSAGE_SEND));
    }

    @Override
    public void run(SlashCommandEvent event) {
        File file = new File(pathToFile);
        String username = event.getOption("username").getAsString();
        String password = event.getOption("password").getAsString();

        try {
            file.createNewFile();
            int pos = isMigrated(event.getUser().getId(), file);
            FileOutputStream outputStream;
            if(pos > -1){
                outputStream = new FileOutputStream(file);
                String newText = insertByIndex(pos, file, username, password);
                outputStream.write(newText.getBytes(StandardCharsets.UTF_8));
            } else{
                outputStream = new FileOutputStream(file, true);
                outputStream.write(MessageFormat.format("{0}:{1}:{2}\n", event.getUser().getId(), username, password).getBytes(StandardCharsets.UTF_8));
            }
            outputStream.close();
            event.reply("Succeed migrated!").setEphemeral(true).queue();
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
            event.reply("There's an error while migrating, please contact admin for more help!").setEphemeral(true).queue();
        }
    }

    /**
     * Check is user migrated? return -1 if not
     * @param userId
     * @param file
     * @return
     */
    private int isMigrated(String userId, File file){
        try {
            String[] lines = Files.lines(file.toPath()).toArray(String[]::new);
            for (int i = 0; i < lines.length; i++) {
                String[] line_arr = lines[i].split(":");
                if(line_arr[0].equals(userId)) return i;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        return -1;
    }

    private String insertByIndex(int index, File file, String username, String password){
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            List<String> line_list = Arrays.stream(lines.get(index).split(":")).toList();
            line_list.set(1, username);
            line_list.set(2, password);
            lines.set(index, String.join(":", line_list));
            return String.join("\n", lines);
        } catch (IOException e) {
            logger.error(e.getMessage(), e.getCause());
        }
        return "";
    }
}
