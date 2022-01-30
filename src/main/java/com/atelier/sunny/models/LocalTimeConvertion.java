package com.atelier.sunny.models;

import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.bson.Document;

import javax.annotation.Nullable;
import java.time.LocalTime;
import java.time.ZoneId;

public enum LocalTimeConvertion {
    EMORNING,
    EAFTERNOON,
    ENIGHT,
    EUNKNOWN;
    public static final ZoneId timeZone = ZoneId.of("Asia/Ho_Chi_Minh");
    public static final LocalTime MORNING = LocalTime.of(6, 0, 0); // 6:00
    public static final LocalTime MORNING_LATE = MORNING.plusMinutes(30); // 6:30
    public static final LocalTime AFTERNOON = LocalTime.of(12, 0, 0); // 12:00 PM
    public static final LocalTime AFTERNOON_LATE = AFTERNOON.plusMinutes(30); // 12:30 PM
    public static final LocalTime NIGHT = LocalTime.of(20, 0,0); // 18:00 -> 6:00 PM
    public static final LocalTime NIGHT_LATE = NIGHT.plusMinutes(30); // 18:30 -> 6:30 PM

    public static LocalTimeConvertion checkTime(LocalTime time) {
        if (time.isAfter(NIGHT) && time.isBefore(NIGHT_LATE)){
            return LocalTimeConvertion.ENIGHT;
        }else if (time.isAfter(AFTERNOON) && time.isBefore(AFTERNOON_LATE)){
            return LocalTimeConvertion.EAFTERNOON;
        }else if (time.isAfter(MORNING) && time.isBefore(MORNING_LATE)){
            return LocalTimeConvertion.EMORNING;
        }else
            return LocalTimeConvertion.EUNKNOWN;
    }

    @Nullable
    public static MessageEmbed getEmbed(Guild guild){
        LocalTimeConvertion localTimeConvertion = checkTime(LocalTime.now(timeZone));
        Document document = DatabaseUtils.getDocument("guildID", guild.getId());
        switch (localTimeConvertion){
            case ENIGHT:
                return DefaultEmbed.NIGHT(document);
            case EMORNING:
                return DefaultEmbed.MORNING(document);
            case EAFTERNOON:
                return DefaultEmbed.AFTERNOON(document);
        }
        return null;
    }
}
