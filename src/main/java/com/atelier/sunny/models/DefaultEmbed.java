package com.atelier.sunny.models;

import com.atelier.sunny.Bot;
import com.atelier.sunny.utils.URLUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bson.Document;

import java.awt.Color;
import java.net.URI;
import java.net.URL;
import java.util.List;


public final class DefaultEmbed {
    public static MessageEmbed MORNING(Document document){
        String IMAGE = "https://cdn.discordapp.com/attachments/923148607157329961/935933903577960509/Atri-Genesus.png";

        Guild guild = Bot.jda.getGuildById(document.get("guildID", String.class));
        if (guild == null) throw new NullPointerException("Unknown guild id "+document.get("guildID", String.class));

        List<String> imageUrl = document.getList("imageUrl", String.class);
        if (URLUtils.isValid(imageUrl.get(0))) IMAGE = imageUrl.get(0);
        return new EmbedBuilder()
                .setTitle("Rise and shine! :atri:")
                .setColor(new Color(4321431))
                .setImage(IMAGE)
                .setAuthor(guild.getName(), "https://discord.gg/", guild.getIconUrl())
                .build();
    }

    public static MessageEmbed AFTERNOON(Document document){
        String IMAGE = "https://cdn.discordapp.com/attachments/923148607157329961/935931768832397392/unknown.png";

        Guild guild = Bot.jda.getGuildById(document.get("guildID", String.class));
        if (guild == null) throw new NullPointerException("Unknown guild id "+document.get("guildID", String.class));

        List<String> imageUrl = document.getList("imageUrl", String.class);
        if (URLUtils.isValid(imageUrl.get(1))) IMAGE = imageUrl.get(1);
        return new EmbedBuilder()
                .setTitle("You are as bright as the afternoon sun ")
                .setColor(new Color(15442529))
                .setImage(IMAGE)
                .setAuthor(guild.getName(), "https://discord.gg/", guild.getIconUrl())
                .build();
    }
    public static MessageEmbed NIGHT(Document document){
        String IMAGE = "https://cdn.discordapp.com/attachments/923148607157329961/923148735465259008/1592906132.png";

        Guild guild = Bot.jda.getGuildById(document.get("guildID", String.class));
        if (guild == null) throw new NullPointerException("Unknown guild id "+document.get("guildID", String.class));

        List<String> imageUrl = document.getList("imageUrl", String.class);
        if (URLUtils.isValid(imageUrl.get(2))) IMAGE = imageUrl.get(2);
        return new EmbedBuilder()
                .setTitle("This could be the end of the day, but soon there will be a new day.")
                .setColor(new Color(5460819))
                .setImage(IMAGE)
                .setAuthor(guild.getName(), "https://discord.gg/", guild.getIconUrl())
                .build();
    }

}
