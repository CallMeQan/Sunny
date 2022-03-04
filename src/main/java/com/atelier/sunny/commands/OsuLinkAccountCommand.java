package com.atelier.sunny.commands;

import com.atelier.sunny.Bot;
import com.atelier.sunny.manager.command.Command;
import com.atelier.sunny.models.OsuDocument;
import com.atelier.sunny.utils.DatabaseUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.kane.osu4j.Base.User;
import tw.kane.osu4j.Exception.InvalidTokenException;
import tw.kane.osu4j.Exception.NotFoundException;
import tw.kane.osu4j.Mode;

import java.io.IOException;
import java.text.MessageFormat;

public class OsuLinkAccountCommand extends Command {
    private static Logger logger = LoggerFactory.getLogger(OsuLinkAccountCommand.class);
    public OsuLinkAccountCommand() {
        super("osulink", "Link your osu account", new String[]{"username"}, null, new String[]{"osulink", "osuset"});
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
        String memberId = event.getMember().getId();
        String username = args[0].replaceAll("_", " ");
        OsuDocument doc = OsuDocument.convertDocument(DatabaseUtils.getDocument("guildId", event.getGuild().getId(), DatabaseUtils.CollName.OSU));
        try {
            User osuUser = Bot.osuClient.getUser(username, Mode.OSU, false);
            Document users = doc.getUsers();
            if(users.getString(memberId) == null)
                users.append(memberId, osuUser.name);
            else
                users.replace(memberId, osuUser.name);
            doc.setUsers(users).update();

            event.getMessage()
                    .reply(MessageFormat.format(":white_check_mark: `{0}` has changed their user to `{1}`", event.getAuthor().getName(), username))
                    .queue();
        }catch (NotFoundException e){
            event.getMessage().reply("User `"+args[0]+"` is not found").queue();
        }catch (IOException | InvalidTokenException e) {
            logger.error(e.getMessage(), e.getCause());
        }

    }
}
