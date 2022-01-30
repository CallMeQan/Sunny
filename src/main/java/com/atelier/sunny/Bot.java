package com.atelier.sunny;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public enum Bot {
    INSTANCE;
    public static final String PREFIX = System.getenv("DISCORD_PREFIX");
    public static JDA jda;
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    public void init() {
        try {

            jda = JDABuilder.createDefault(
                    // As Intellij IDEA has option to run gradle with custom env key, so we don't have to do this manually
                    System.getenv("DISCORD_TOKEN"),
                    GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS
            ).addEventListeners(new Listener())
             .setActivity(Activity.playing("AtelierSMP: https://discord.gg/ateliermc"))
             .setIdle(true).build();
        } catch (LoginException e) {
            logger.error(e.getMessage(), e.getCause());
            System.exit(1);
        }
    }

    public static void shutdown(){
        jda.shutdownNow();
    }
}
