package com.atelier.sunny;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum Storage {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(Storage.class);
    private static final List<BasicInformation> guilds = new ArrayList<>();
    private static final JDA bot = Bot.jda;

    public void updateGuilds(){
        bot.getGuilds().forEach(guild -> {
            guilds.add(new BasicInformation(guild.getId(), guild.getName(), guild.getSystemChannel().getId()));
            logger.info("Adding "+guild.getName()+" to storage");
        });
    }

    /**
     * Basically this is update method for List, but also an add method for List too :D
     * @param guild you know what it is
     */
    public void updateGuild(Guild guild){
        for (BasicInformation guildInfo: guilds) {
            if (guild.getId() == guildInfo.id){
                guilds.set(guilds.indexOf(guildInfo), new BasicInformation(guild.getId(), guild.getName(), guild.getSystemChannel().getId()));
                return;
            }
        }
        guilds.add(new BasicInformation(guild.getId(), guild.getName(), guild.getSystemChannel().getId()));
    }

    public void updateGuild(BasicInformation guildInfo){
        for (BasicInformation info: guilds){
            if (guildInfo.id == info.id){
                guilds.set(guilds.indexOf(info), guildInfo);
                return;
            }
        }
        guilds.add(guildInfo);
    }

    public BasicInformation getGuildById(String id){
        for (BasicInformation guildInfo: guilds) {
            if (guildInfo.id() == id) return guildInfo; // Getting it normally if exist in List
        }
        // Else add new information to list and return it
        Guild guild = bot.getGuildById(id);
        BasicInformation info = new BasicInformation(guild.getId(), guild.getName(), guild.getSystemChannel().getId());
        guilds.add(info);
        return info;
    }

    public List<BasicInformation> getGuilds() {
        return guilds;
    }

    public record BasicInformation(String id, String name, String channelId) {
        public BasicInformation setChannelId(String newId) { return new BasicInformation(id, name, newId); }
    }

}

