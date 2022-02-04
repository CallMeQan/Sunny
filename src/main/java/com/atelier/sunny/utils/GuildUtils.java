package com.atelier.sunny.utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class GuildUtils {
    public static TextChannel getFirstTextChannel(Guild guild){
        return guild.getTextChannels().get(0);
    }
}
