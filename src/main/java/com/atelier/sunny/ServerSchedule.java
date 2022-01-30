package com.atelier.sunny;

import com.atelier.sunny.manager.event.BetterTimerTask;
import com.atelier.sunny.models.DefaultEmbed;
import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.models.LocalTimeConvertion;
import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

public class ServerSchedule extends BetterTimerTask {
    private final Logger logger = LoggerFactory.getLogger(ServerSchedule.class);

    private final GuildDocument information;
    public ServerSchedule(GuildDocument info) {
        super(info.getGuildID());
        information = info;
    }

    @Override
    public void run() {
        Guild guild = Bot.jda.getGuildById(information.getGuildID());
        TextChannel channel = guild.getTextChannelById(information.getChannelID());
        logger.info("Task perform on '" + guild.getName() + "'");

        if (channel != null) {
            MessageEmbed embed = LocalTimeConvertion.getEmbed(guild);
            // test(channel, guild);
            if (embed != null) {
                channel.sendMessageEmbeds(embed).complete();
                logger.info("Sent message to '" + guild.getName() + "'");
                return;
            }
            logger.info("Unknown time: " + LocalTime.now(LocalTimeConvertion.timeZone));
            return;
        }
        logger.info("Channel or guild not found: "+ guild.getId() + " with channelId is "+information.getGuildID());
    }

    private void test(TextChannel channel, Guild guild) {
        Document document = DatabaseUtils.getDocument("guildID", guild.getId());
        channel.sendMessageEmbeds(DefaultEmbed.MORNING(document)).complete();
        channel.sendMessageEmbeds(DefaultEmbed.AFTERNOON(document)).complete();
        channel.sendMessageEmbeds(DefaultEmbed.NIGHT(document)).complete();
    }
}
