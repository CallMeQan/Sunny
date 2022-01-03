package com.atelier.sunny.events;

import com.atelier.sunny.Bot;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;

public class BotAction extends ListenerAdapter {
    private final Logger logger = LogManager.getLogger(BotAction.class);
    private final Timer timer = new Timer();
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        logger.info("Bot is ready");
        System.out.println("Bot is ready");
        //timer.schedule(new AutoMessage(), 0, 300000);
    }

    @Override
    public void onDisconnect(@NotNull DisconnectEvent event) {
        super.onDisconnect(event);
    }
}
