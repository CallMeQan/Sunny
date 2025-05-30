package com.atelier.sunny.models;

import com.atelier.sunny.Bot;
import com.atelier.sunny.utils.URLUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bson.Document;

import java.awt.Color;


public final class DefaultEmbed {
    public static MessageEmbed MORNING(Document document){
        String IMAGE = "https://cdn.discordapp.com/attachments/814127567699181610/943094281403531264/Atri-SummerGrass.png";
        String TITLE = "Rise and shine!";

        Guild guild = Bot.jda.getGuildById(document.get("guildID", String.class));
        if (guild == null) throw new NullPointerException("Unknown guild id "+document.get("guildID", String.class));

        String imageUrl = document.getList("imageUrl", String.class).get(0);
        String texts = document.getList("texts", String.class).get(0);

        if (URLUtils.isValid(imageUrl)) IMAGE = imageUrl;
        if (!texts.trim().isEmpty()) TITLE = texts;
        return new EmbedBuilder()
                .setTitle(TITLE)
                .setColor(new Color(4321431))
                .setImage(IMAGE)
                .setAuthor(guild.getName(), "https://discord.gg/", guild.getIconUrl())
                .build();
    }

    public static MessageEmbed AFTERNOON(Document document){
        String IMAGE = "https://cdn.discordapp.com/attachments/923148607157329961/935931768832397392/unknown.png";
        String TITLE = "You are as bright as the afternoon sun.";

        Guild guild = Bot.jda.getGuildById(document.get("guildID", String.class));
        if (guild == null) throw new NullPointerException("Unknown guild id "+document.get("guildID", String.class));

        String imageUrl = document.getList("imageUrl", String.class).get(1);
        String texts = document.getList("texts", String.class).get(1);

        if (URLUtils.isValid(imageUrl)) IMAGE = imageUrl;
        if (!texts.trim().isEmpty()) TITLE = texts;
        return new EmbedBuilder()
                .setTitle(TITLE)
                .setColor(new Color(15442529))
                .setImage(IMAGE)
                .setAuthor(guild.getName(), "https://discord.gg/", guild.getIconUrl())
                .build();
    }
    public static MessageEmbed NIGHT(Document document){
        String IMAGE = "https://cdn.discordapp.com/attachments/923148607157329961/923148735465259008/1592906132.png";
        String TITLE = "This could be the end of the day, but soon there will be a new day.";

        Guild guild = Bot.jda.getGuildById(document.get("guildID", String.class));
        if (guild == null) throw new NullPointerException("Unknown guild id "+document.get("guildID", String.class));

        String imageUrl = document.getList("imageUrl", String.class).get(2);
        String texts = document.getList("texts", String.class).get(2);

        if (URLUtils.isValid(imageUrl)) IMAGE = imageUrl;
        if (!texts.trim().isEmpty()) TITLE = texts;
        return new EmbedBuilder()
                .setTitle(TITLE)
                .setColor(new Color(5460819))
                .setImage(IMAGE)
                .setAuthor(guild.getName(), "https://discord.gg/", guild.getIconUrl())
                .build();
    }

}
