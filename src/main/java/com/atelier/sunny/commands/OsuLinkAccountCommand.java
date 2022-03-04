package com.atelier.sunny.commands;

import com.atelier.sunny.manager.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OsuLinkAccountCommand extends Command {
    private static Logger logger = LoggerFactory.getLogger(OsuLinkAccountCommand.class);
    public OsuLinkAccountCommand() {
        super("osulink", "Link your osu account", new String[]{"username"}, null, new String[]{"osulink", "osuset"});
    }

    @Override
    public void run(MessageReceivedEvent event, String[] args) {
//        String memberId = event.getMember().getId();
//        String username = args[0].replaceAll("_", " ");
//        OsuDocument doc = OsuDocument.convertDocument(DatabaseUtils.getDocument("guildId", event.getGuild().getId(), DatabaseUtils.CollName.OSU));
//        try {
//            User osuUser = Bot.osuClient.getUser(username, Mode.OSU, false);
//            Document users = doc.getUsers();
//            if(users.getString(memberId) == null)
//                users.append(memberId, osuUser.name);
//            else
//                users.replace(memberId, osuUser.name);
//            doc.setUsers(users).update();
//
//            event.getMessage()
//                    .reply(MessageFormat.format(":white_check_mark: `{0}` has changed their user to `{1}`", event.getAuthor().getName(), username))
//                    .queue();
//        }catch (NotFoundException e){
//            event.getMessage().reply("User `"+args[0]+"` is not found").queue();
//        }catch (IOException | InvalidTokenException e) {
//            logger.error(e.getMessage(), e.getCause());
//        }

    }
}
