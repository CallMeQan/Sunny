package com.atelier.sunny.models;

import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bson.Document;

import javax.annotation.Nullable;
import java.time.LocalTime;
import java.time.ZoneId;

public enum LocalTimeConvertion {
    MORNING(LocalTime.of(6, 0, 0)),
    MORNING_LATE(MORNING.time.plusMinutes(30)),
    AFTERNOON(LocalTime.of(12, 0, 0)),
    AFTERNOON_LATE(AFTERNOON.time.plusMinutes(30)),
    NIGHT(LocalTime.of(20, 0,0)),
    NIGHT_LATE(NIGHT.time.plusMinutes(30)),
    UNKNOWN(LocalTime.MAX),
    TIMEZONE(ZoneId.of("Asia/Ho_Chi_Minh"));

    public LocalTime time;
    public ZoneId timeZone;

    LocalTimeConvertion(LocalTime time){
        this.time = time;
    }

    LocalTimeConvertion(ZoneId timeZone){
        this.timeZone = timeZone;
    }

    public static LocalTimeConvertion checkTime(LocalTime time) {
        if (time.isAfter(LocalTimeConvertion.NIGHT.time) && time.isBefore(LocalTimeConvertion.NIGHT_LATE.time)){
            return LocalTimeConvertion.NIGHT;
        }else if (time.isAfter(LocalTimeConvertion.AFTERNOON.time) && time.isBefore(LocalTimeConvertion.AFTERNOON_LATE.time)){
            return LocalTimeConvertion.AFTERNOON;
        }else if (time.isAfter(LocalTimeConvertion.MORNING.time) && time.isBefore(LocalTimeConvertion.MORNING_LATE.time)){
            return LocalTimeConvertion.MORNING;
        }else
            return LocalTimeConvertion.UNKNOWN;
    }

    @Nullable
    public static MessageEmbed getEmbed(Guild guild){
        LocalTimeConvertion localTimeConvertion = checkTime(LocalTime.now(LocalTimeConvertion.TIMEZONE.timeZone));
        Document document = DatabaseUtils.getDocument("guildID", guild.getId(), DatabaseUtils.CollName.GUILD);
        return switch (localTimeConvertion) {
            case NIGHT -> DefaultEmbed.NIGHT(document);
            case MORNING -> DefaultEmbed.MORNING(document);
            case AFTERNOON -> DefaultEmbed.AFTERNOON(document);
            default -> null;
        };
    }
}
