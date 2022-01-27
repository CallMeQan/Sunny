package com.atelier.sunny.events;

import com.atelier.sunny.Storage;
import com.atelier.sunny.event.EventManager;
import com.atelier.sunny.models.ServerSchedule;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BotAction extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(BotAction.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);
        Storage.INSTANCE.updateGuilds();
        setupTimer();
        EventManager.startAllTimer();
        logger.info("Bot is ready");
    }

    private void setupTimer() {
        for (Storage.BasicInformation info: Storage.INSTANCE.getGuilds()) {
            EventManager.addTimer(new ServerSchedule(info));
            logger.info("Added "+info.name()+" to timer task");
        }
    }

    @Override
    public void onDisconnect(@NotNull DisconnectEvent event) {
        EventManager.shutdownTimer();
        super.onDisconnect(event);
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        EventManager.shutdownTimer();
        super.onShutdown(event);
    }
}
