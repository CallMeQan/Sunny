package com.atelier.sunny.commands;

import com.atelier.sunny.manager.command.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SlapCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(SlapCommand.class);
    public SlapCommand() {
        super();
        this.data = new CommandData("slap", "SLAP, SLAP AND JUST SLAP YOUR FUCKING FRIEND :D")
                .addOption(OptionType.USER, "user", "Whose you want to slap", true);
        this.perms = List.of(Permission.MESSAGE_SEND);
    }
    @Override
    public void run(SlashCommandEvent event) {
        // Haha, slapping everyone even they were not in that guild go brrrrrrrrrrrrrrrr
        User user = event.getOption("user").getAsUser();
        try {
            URL API_URL = new URL("https://nekos.life/api/v2/img/slap");
            JSONObject jsonObject = readJsonFromUrl(API_URL);
            MessageEmbed embed = new EmbedBuilder()
                    .setDescription(event.getMember().getAsMention() + " just **slapped** "+ user.getAsMention())
                    .setColor(Color.RED)
                    .setImage(jsonObject.getString("url"))
                    .build();
            event.deferReply().addEmbeds(embed).queue();
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            event.reply("Unknown exception cause this, pls ping bot owner to fix this").queue();
        }
    }

    private static JSONObject readJsonFromUrl(URL url) throws IOException, JSONException {
        try (InputStream is = url.openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
