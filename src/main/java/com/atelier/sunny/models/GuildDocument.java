package com.atelier.sunny.models;

import com.atelier.sunny.utils.DatabaseUtils;
import org.bson.Document;

import java.util.List;

public final class GuildDocument {
    private String guildID;
    private String guildName;
    private String channelID = null;
    private boolean run = false;
    // Making imageUrl as default is better
    private List<String> imageUrl = List.of(
            "https://cdn.discordapp.com/attachments/923148607157329961/935933903577960509/Atri-Genesus.png",
            "https://cdn.discordapp.com/attachments/923148607157329961/935931768832397392/unknown.png",
            "https://cdn.discordapp.com/attachments/923148607157329961/923148735465259008/1592906132.png"
    );

    private List<String> texts = List.of(
            "Rise and shine!",
            "You are as bright as the afternoon sun.",
            "This could be the end of the day, but soon there will be a new day."
    );

    public List<String> getTexts() { return texts;}
    public List<String> getImageUrl() { return imageUrl; }
    public String getChannelID() { return channelID; }
    public String getGuildID() { return guildID; }
    public String getGuildName() { return guildName; }
    public boolean isRun() { return run; }

    public GuildDocument setText(int index, String value) {
        this.texts.set(index, value);
        return this;
    }

    public GuildDocument setTexts(List<String> texts) {
        this.texts = texts;
        return this;
    }

    public GuildDocument setImageUrl(int index, String value) {
        this.imageUrl.set(index, value);
        return this;
    }

    public GuildDocument setImageUrl(List<String> url){
        this.imageUrl = url;
        return this;
    }

    public GuildDocument setChannelID(String channelID) {
        this.channelID = channelID;
        return this;
    }

    public GuildDocument setGuildId(String guildID) {
        this.guildID = guildID;
        return this;
    }

    public GuildDocument setGuildName(String guildName) {
        this.guildName = guildName;
        return this;
    }

    public GuildDocument setRun(Boolean isRun) {
        this.run = isRun;
        return this;
    }

    public void update() {
        DatabaseUtils.updateDocument("guildID", this.guildID, toDoc(), DatabaseUtils.CollName.GUILD);
    }

    public Document toDoc(){
        return new Document()
                .append("guildID", this.guildID)
                .append("guildName", this.guildName)
                .append("channelID", this.channelID)
                .append("isRun", this.run)
                .append("imageUrl", this.imageUrl)
                .append("texts", this.texts);
    }

    public static GuildDocument convertDocument(Document doc){
        if (doc == null) return null;
        return new GuildDocument()
                .setImageUrl(doc.getList("imageUrl", String.class))
                .setGuildId(doc.getString("guildID"))
                .setGuildName(doc.getString("guildName"))
                .setRun(doc.getBoolean("isRun"))
                .setChannelID(doc.getString("channelID"))
                .setTexts(doc.getList("texts", String.class));
    }

}
