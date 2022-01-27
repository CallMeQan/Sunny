package com.atelier.sunny.models;

import com.atelier.sunny.Bot;
import com.atelier.sunny.Storage;
import com.atelier.sunny.event.BetterTimerTask;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

public class ServerSchedule extends BetterTimerTask {
    private final Logger logger = LoggerFactory.getLogger(ServerSchedule.class);

    private final Storage.BasicInformation information;
    public ServerSchedule(Storage.BasicInformation info) {
        super(info.id());
        information = info;
    }

    @Override
    public void run() {
        Guild guild = Bot.jda.getGuildById(information.id());
        TextChannel channel;
        logger.info("Task perform on '" + guild.getName() + "'");

        if (guild.getId() == "814127567207530527"){
            channel = guild.getTextChannelById("814127567699181609"); // # 💬︱chat
        }else{
            channel = guild != null ? guild.getSystemChannel() : null;
        }
        if (channel != null) {
            MessageEmbed embed = LocalTimeConvertion.getEmbed(guild);
            if (embed != null) {
                test(channel, guild);
                // channel.sendMessageEmbeds(embed).complete();
                logger.info("Sent message to '" + guild.getName() + "'");
                return;
            }
            logger.info("Unknown time: " + LocalTime.now(LocalTimeConvertion.timeZone));
            return;
        }
        logger.info("Channel or guild not found: "+ guild.getId() + " with channelId is "+information.channelId());
    }

    private void test(TextChannel channel, Guild guild) {
        channel.sendMessageEmbeds(DefaultEmbed.MORNING(guild)).complete();
        channel.sendMessageEmbeds(DefaultEmbed.AFTERNOON(guild)).complete();
        channel.sendMessageEmbeds(DefaultEmbed.NIGHT(guild)).complete();
    }
}
