package com.atelier.sunny;

import com.atelier.sunny.manager.event.BetterTimerTask;
import com.atelier.sunny.models.GuildDocument;
import com.atelier.sunny.models.LocalTimeConvertion;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
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
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById(information.getChannelID());
            logger.info("Task checked on \"" + guild.getName() + "\"");
            MessageEmbed embed = LocalTimeConvertion.getEmbed(guild);

            if (channel != null && embed != null) {
                if (information.isRun() == false) {
                    channel.sendMessageEmbeds(embed).complete();
                    logger.info("Sent message to \"" + guild.getName() + "\"");
                    information.setRun(true).update();
                    return;
                }
                logger.info("Task already ran on \""+guild.getName()+"\"");
                return;
            }else if (channel != null && embed == null){
                information.setRun(false).update();
                logger.info("Unknown time: " + LocalTime.now(LocalTimeConvertion.timeZone));
                return;
            }
            logger.info("Channel or guild not found: "+ guild.getId() + " with channelId is "+information.getGuildID());
        }
    }
}
