package com.atelier.sunny.events;

import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuildEvent extends ListenerAdapter {
    private final Logger logger = LoggerFactory.getLogger(GuildEvent.class);

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        super.onGuildJoin(event);
        DatabaseUtils.createDocument(new Document()
                .append("guildID", event.getGuild().getId())
                .append("guildName", event.getGuild().getName())
        );
        logger.info("Bot join " + event.getGuild().getName());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        super.onGuildLeave(event);
        DatabaseUtils.deleteDocument("guildID", event.getGuild().getId());
        logger.info("Bot leave " + event.getGuild().getName());
    }
}
