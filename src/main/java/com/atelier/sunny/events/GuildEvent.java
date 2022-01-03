package com.atelier.sunny.events;

import com.atelier.sunny.Bot;
import com.atelier.sunny.Storage;
import com.atelier.sunny.models.StorageModel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuildEvent extends ListenerAdapter {
    private final Logger logger = LogManager.getLogger(GuildEvent.class);

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        Guild guild = event.getGuild();
        if (Bot.storage.json.getGuildById(guild.getId()) == null){
            List<StorageModel.GuildModel> guilds = new ArrayList<>(Arrays.asList(Bot.storage.json.getGuilds()));
            guilds.add(Storage.createDefaultGuildModel(guild));

            StorageModel.GuildModel[] arr = guilds.toArray(StorageModel.GuildModel[]::new);
            Bot.storage.json.setGuilds(arr);
            Bot.storage.save();
        }
        logger.info("Bot join guild with id "+guild.getId());
        super.onGuildJoin(event);
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        Guild guild = event.getGuild();
        StorageModel.GuildModel json =  Bot.storage.json.getGuildById(event.getGuild().getId());
        StorageModel.GuildModel[] guilds = Bot.storage.json.getGuilds();
        if (json != null && !json.isKeepOnLeave()){
            List<StorageModel.GuildModel> guildModelList = Arrays.stream(guilds).toList();
            if (!guildModelList.remove(json)) {logger.fatal("Can't remove guild "+json.getId()+" from List<StorageModel.GuildModel>"); return;}
            Bot.storage.json.setGuilds((StorageModel.GuildModel[]) guildModelList.toArray());
            Bot.storage.save();
        }
        logger.info("Left guild "+ guild.getName()+" with id "+guild.getId());
        super.onGuildLeave(event);
    }
}
