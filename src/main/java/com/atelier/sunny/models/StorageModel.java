package com.atelier.sunny.models;

import com.atelier.sunny.command.CommandList;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageModel {
    private GuildModel[] guilds;

    public void setGuilds(GuildModel[] guilds) {
        this.guilds = guilds;
    }

    public GuildModel[] getGuilds() {
        return guilds;
    }

    public void addGuild(GuildModel guildModel){
        List<GuildModel> guildModels = Arrays.stream(getGuilds()).toList();
        guildModels.add(guildModel);
        setGuilds(guildModels.toArray(StorageModel.GuildModel[]::new));
    }

    /**
     * Get model by id that `newModel` provided then replace it
     * @param newModel
     */
    public void editGuild(GuildModel newModel){
        List<GuildModel> guildModels = Arrays.stream(getGuilds()).toList();
        for (GuildModel guildModel: guildModels) {
            if (guildModel.getId() == newModel.getId()){
                int index = guildModels.indexOf(guildModel);
                guildModels.set(index, newModel);
            }
        }
        setGuilds(guildModels.toArray(StorageModel.GuildModel[]::new));
    }

    @Nullable
    public GuildModel getGuildById(String id) {
        for (GuildModel guildModel: getGuilds()) if (id == guildModel.id) return guildModel;
        return null;
    }

    public static class GuildModel{
        public void setId(String id) {
            this.id = id;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setKeepOnLeave(boolean keepOnLeave) {
            this.keepOnLeave = keepOnLeave;
        }

        public void setImJustPing(boolean imJustPing) {
            isImJustPing = imJustPing;
        }

        public String getId() {
            return id;
        }

        public String getChannelId() {
            return channelId;
        }

        public String getPrefix() {
            return prefix;
        }

        public boolean isKeepOnLeave() {
            return keepOnLeave;
        }

        public boolean isImJustPing() {
            return isImJustPing;
        }

        private String id;
        private String channelId;
        private String prefix;
        private boolean keepOnLeave;
        private boolean isImJustPing;
    }
}
