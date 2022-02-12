package com.atelier.sunny.manager;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.MessageFormat;

public class CustomMessage {
    public static MessageEmbed ipEmbed = new EmbedBuilder()
            .setTitle("AtelierMC IP")
            .setDescription("__**Địa chỉ để kết nối đến AtelierMC**__")
            .addField("IP Java", "play.ateliermc.tech | play.atelier-mc.ga", true)
            .addField("IP Bedrock", "bedrock.ateliermc.tech", true)
            .addField("Phiên bản", "1.16 - 1.18 | Lastest Bedrock version (not Beta)", false)
            .setFooter("AtelierMC")
            .setColor(Color.CYAN)
            .build();
    // For AtelierSMP or AtelierMC
    public static void process(MessageReceivedEvent event) {
        if(!event.getGuild().getId().equals("814127567207530527")) return;

        switch (event.getMessage().getContentDisplay()) {
            case "!at online" -> event.getMessage().reply("Thg lon thuancay nó xóa cái command này rồi nên không biết đâu").queue();
            case "!at ip" -> event.getMessage().replyEmbeds(ipEmbed).queue();
        }
    }
}
