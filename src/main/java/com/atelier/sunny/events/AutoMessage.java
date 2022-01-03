package com.atelier.sunny.events;

import com.atelier.sunny.Bot;
import com.atelier.sunny.models.LocalTimeConvertion;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.LocalTime;
import java.util.TimerTask;

public class AutoMessage extends TimerTask {
    private final LocalTime MORNING = LocalTime.of(7, 0, 0); // 7:00
    private final LocalTime MORNING_LATE = MORNING.plusMinutes(30); // 7:30
    private final LocalTime AFTERNOON = LocalTime.of(16, 0, 0); // 16:00 -> 4:00 PM
    private final LocalTime AFTERNOON_LATE = AFTERNOON.plusMinutes(30); // 16:30 -> 4:30 PM
    private final LocalTime NIGHT = LocalTime.of(18, 0,0); // 18:00 -> 6:00 PM
    private final LocalTime NIGHT_LATE = NIGHT.plusMinutes(30); // 18:30 -> 6:30 PM
    private final JDA jda;

    public AutoMessage(){
        this.jda = Bot.jda;
    }

    public LocalTimeConvertion checkTime(LocalTime time) {
        if (time.isAfter(NIGHT) && time.isBefore(NIGHT_LATE)){
            return LocalTimeConvertion.NIGHT;
        }else if (time.isAfter(AFTERNOON) && time.isBefore(AFTERNOON_LATE)){
            return LocalTimeConvertion.AFTERNOON;
        }else if (time.isAfter(MORNING) && time.isBefore(MORNING_LATE)){
            return LocalTimeConvertion.MORNING;
        }else
            return LocalTimeConvertion.UNKNOWN;
    }

    @Override
    public void run() {
        LocalTimeConvertion result = checkTime(LocalTime.now());
        if (result != LocalTimeConvertion.UNKNOWN) {
            Guild guild = jda.getGuildById("793354405516541972");
            if (guild != null){
                TextChannel textChannel = guild.getTextChannelById("800353287479361547");
                if (textChannel != null){
                    textChannel.sendMessage("@everyone "+result.toString()).complete();
                }else{
                }
            }else {
            }
        }
    }
}
