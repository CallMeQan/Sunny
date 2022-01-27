package com.atelier.sunny.events;

import com.atelier.sunny.Storage;
import com.atelier.sunny.event.EventManager;
import com.atelier.sunny.models.ServerSchedule;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuildEvent extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(GuildEvent.class);

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        String id = event.getGuild().getId();
        Storage.INSTANCE.updateGuild(event.getGuild());

        EventManager.addTimer(new ServerSchedule(Storage.INSTANCE.getGuildById(id)));
        EventManager.startTimer(id);

        logger.info("Bot join '"+event.getGuild().getName()+ "' with id " + id);
        super.onGuildJoin(event);
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        String id = event.getGuild().getId();
        Storage.INSTANCE.updateGuild(event.getGuild());

        EventManager.shutdownTask(id);
        logger.info("Bot leave '"+event.getGuild().getName()+ "' with id " + id);
        super.onGuildLeave(event);
    }
}
