package com.atelier.sunny.models;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.awt.*;

public final class DefaultEmbed {
    public static MessageEmbed MORNING(Guild guild){
        return new EmbedBuilder()
                .setTitle("Rise and shine! :atri:")
                .setColor(new Color(4321431))
                .setImage("https://cdn.discordapp.com/attachments/923148607157329961/935933903577960509/Atri-Genesus.png")
                .setAuthor("AtelierSMP", "https://discord.gg/ateliermc", guild.getIconUrl())
                .build();
    }
    public static MessageEmbed AFTERNOON(Guild guild){
        return new EmbedBuilder()
                .setTitle("You are as bright as the afternoon sun ")
                .setColor(new Color(15442529))
                .setImage("https://cdn.discordapp.com/attachments/923148607157329961/935931768832397392/unknown.png")
                .setAuthor("AtelierSMP", "https://discord.gg/ateliermc", guild.getIconUrl())
                .build();
    }
    public static MessageEmbed NIGHT(Guild guild){
        return new EmbedBuilder()
                .setTitle("This could be the end of the day, but soon there will be a new day.")
                .setColor(new Color(5460819))
                .setImage("https://cdn.discordapp.com/attachments/923148607157329961/923148735465259008/1592906132.png")
                .setAuthor("AtelierSMP", "https://discord.gg/ateliermc", guild.getIconUrl())
                .build();
    }

}
