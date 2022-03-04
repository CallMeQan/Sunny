package com.atelier.sunny.models;

import com.atelier.sunny.utils.DatabaseUtils;
import org.bson.Document;

import java.util.List;

public final class OsuDocument {
    private String guildID;
    private String guildName;
    private Document users;

    public String getGuildID() { return guildID; }
    public String getGuildName() { return guildName; }
    public Document getUsers(){return users;}

    public OsuDocument setGuildId(String guildID) {
        this.guildID = guildID;
        return this;
    }

    public OsuDocument setGuildName(String guildName) {
        this.guildName = guildName;
        return this;
    }

    public OsuDocument setUsers(Document users) {
        this.users = users;
        return this;
    }

    public void update() {
        DatabaseUtils.updateDocument("guildId", this.guildID, toDoc(), DatabaseUtils.CollName.OSU);
    }

    public Document toDoc(){
        return new Document()
                .append("guildId", this.guildID)
                .append("guildName", this.guildName)
                .append("users", this.users);
    }

    public static OsuDocument convertDocument(Document doc){
        if (doc == null) return null;
        return new OsuDocument()
                .setGuildId(doc.getString("guildId"))
                .setGuildName(doc.getString("guildName"))
                .setUsers(doc.get("users", Document.class));

    }
}
