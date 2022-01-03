package com.atelier.sunny.models;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.awt.*;

public final class DefaultEmbed {
    public static final MessageEmbed MORNING(MessageAction msg){
        return new EmbedBuilder()
                .setTitle("**Chào buổi sáng vui vẻ nha mọi người! UwU**")
                .setColor(new Color(7455988))
                .setImage("https://cdn.discordapp.com/attachments/923148607157329961/923148623250866176/137410.png")
                .setAuthor("AtelierSMP", "https://discord.gg/ateliermc", "https://cdn.discordapp.com/embed/avatars/0.png")
                .build();
    }
    public static final MessageEmbed AFTERNOON(String ext){

        return new EmbedBuilder()
                .setTitle("**Chào buổi trưa nắng nóng! UwU**")
                .setColor(new Color(7455988))
                .setImage("https://cdn.discordapp.com/attachments/923148607157329961/923148623250866176/137410.png")
                .setAuthor("AtelierSMP", "https://discord.gg/ateliermc", "https://cdn.discordapp.com/embed/avatars/0.png")
                .build();
    }
    public static final MessageEmbed NIGHT(String ext){
        return new EmbedBuilder()
                .setTitle("**Buổi tối vui vẻ nha mọi người! UwU**")
                .setColor(new Color(7455988))
                .setImage("https://cdn.discordapp.com/attachments/923148607157329961/923148623250866176/137410.png")
                .setAuthor("AtelierSMP", "https://discord.gg/ateliermc", "https://cdn.discordapp.com/embed/avatars/0.png")
                .build();
    }

}
