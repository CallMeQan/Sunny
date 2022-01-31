package com.atelier.sunny.commands;

import com.atelier.sunny.manager.command.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;
import net.dv8tion.jda.internal.utils.IOUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SlapCommand extends Command {
    private final Logger logger = LoggerFactory.getLogger(SlapCommand.class);
    public SlapCommand() {
        super();
        this.data = new CommandData("slap", "Setting your server config")
                .addOption(OptionType.USER, "user", "Whose you want to slap", true);
        this.perms = List.of(Permission.MANAGE_SERVER);
    }
    @Override
    public void run(SlashCommandEvent event) {
        // Haha, slapping everyone even they were not in that guild go brrrrrrrrrrrrrrrr
        User user = event.getOption("user").getAsUser();
        try {
            URL API_URL = new URL("https://nekos.life/api/v2/img/slap");
            JSONObject jsonObject = readJsonFromUrl(API_URL);
            URL IMAGE_URL = new URL(jsonObject.getString("url"));
            byte[] response = getBytesFromUrl(IMAGE_URL);
            event.getChannel().sendMessage(event.getMember().getAsMention() + " just **slapped** "+ user.getAsMention())
                    .addFile(response, "slap.gif")
                    .queue();
            // Currently, there's an error happen here
            // ERROR com.atelier.sunny.commands.SlapCommand - 10062: Unknown interaction net.dv8tion.jda.api.exceptions.ContextException
            // at com.atelier.sunny.commands.SlapCommand.run(SlapCommand.java:42)
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

    private byte[] getBytesFromUrl(URL url) throws IOException {
        return IOUtil.readFully(url.openStream());
    }
}
