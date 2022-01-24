package com.atelier.sunny.models;

import com.atelier.sunny.Bot;
import com.atelier.sunny.Storage;
import com.atelier.sunny.event.BetterTimerTask;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.time.LocalTime;
import java.util.Date;

public class ServerSchedule extends BetterTimerTask {
    private final Logger logger = LoggerFactory.getLogger(ServerSchedule.class);

    private final LocalTime MORNING = LocalTime.of(7, 0, 0); // 7:00
    private final LocalTime MORNING_LATE = MORNING.plusMinutes(30); // 7:30
    private final LocalTime AFTERNOON = LocalTime.of(16, 0, 0); // 16:00 -> 4:00 PM
    private final LocalTime AFTERNOON_LATE = AFTERNOON.plusMinutes(30); // 16:30 -> 4:30 PM
    private final LocalTime NIGHT = LocalTime.of(18, 0,0); // 18:00 -> 6:00 PM
    private final LocalTime NIGHT_LATE = NIGHT.plusMinutes(30); // 18:30 -> 6:30 PM

    private final Storage.BasicInformation information;
    public ServerSchedule(Storage.BasicInformation info) {
        super(info.id());
        information = info;
    }

    private LocalTimeConvertion checkTime(LocalTime time) {
        if (time.isAfter(NIGHT) && time.isBefore(NIGHT_LATE)){
            return LocalTimeConvertion.NIGHT;
        }else if (time.isAfter(AFTERNOON) && time.isBefore(AFTERNOON_LATE)){
            return LocalTimeConvertion.AFTERNOON;
        }else if (time.isAfter(MORNING) && time.isBefore(MORNING_LATE)){
            return LocalTimeConvertion.MORNING;
        }else
            return LocalTimeConvertion.UNKNOWN;
    }

    @Nullable
    private MessageEmbed getEmbed(){
        LocalTimeConvertion localTimeConvertion = checkTime(LocalTime.now());
        switch (localTimeConvertion){
            case NIGHT:
                return DefaultEmbed.NIGHT();
            case MORNING:
                return DefaultEmbed.MORNING();
            case AFTERNOON:
                return DefaultEmbed.AFTERNOON();
        }
        return null;
    }

    @Override
    public void run() {
        System.out.println("Task performed on: " + new Date() + "n" + "Thread's name: " + Thread.currentThread().getName());
        Guild guild = Bot.jda.getGuildById(information.id());
        TextChannel channel = guild != null ? guild.getTextChannelById(information.channelId()) : null;
        if (channel != null){
            MessageEmbed embed = getEmbed();
            if(embed != null) {
                channel.sendMessageEmbeds(embed).complete();
                logger.info("Sent message to '" + guild.getName() + "'");
                return;
            }
            logger.info("Unknown time");
            return;
        }
        logger.info("Channel or guild not found: "+ guild.getId() + " with channelId is "+information.channelId());
    }
}
