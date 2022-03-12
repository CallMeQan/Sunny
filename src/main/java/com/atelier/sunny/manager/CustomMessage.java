package com.atelier.sunny.manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class CustomMessage {

    public static MessageEmbed ipEmbed = new EmbedBuilder()
            .setTitle("AtelierMC IP")
            .setDescription("__**Địa chỉ để kết nối đến AtelierMC**__")
            .addField("IP Java", "play.ateliermc.tech | play.atelier-mc.ga", true)
            .addField("IP Bedrock", "bedrock.ateliermc.tech | bedrock.atelier-mc.ga | Port: 19132", false)
            .addField("Phiên bản", "1.17 - 1.18 | Bedrock 1.17.3 trở lên (Không beta)", false)
            .setFooter("AtelierMC")
            .setColor(Color.CYAN)
            .build();
    // For AtelierSMP or AtelierMC
    public static void process(MessageReceivedEvent event) {
        if(!event.getGuild().getId().equals("814127567207530527")) return;

        if ("!at ip".equals(event.getMessage().getContentDisplay())) {
            event.getMessage().replyEmbeds(ipEmbed).queue();
        }
    }
}
