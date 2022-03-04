package com.atelier.sunny.slashCommands;

import com.atelier.sunny.slashCommands.sub.ChannelSetCommand;
import com.atelier.sunny.slashCommands.sub.ImageSetCommand;
import com.atelier.sunny.slashCommands.sub.TextSetCommand;
import com.atelier.sunny.manager.command.SlashCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.List;

public class SetCommand extends SlashCommand {
    public SetCommand() {
        super(new CommandData("set", "Setting your server config")
                .addSubcommands(
                        new SubcommandData("channel", "Set the channel")
                                .addOption(OptionType.CHANNEL, "channel", "Channel to send msg", true),
                        new SubcommandData("image", "Set the Image")
                                .addOptions(
                                        new OptionData(OptionType.STRING, "morning", "Customize your own morning image"),
                                        new OptionData(OptionType.STRING, "afternoon", "Customize your own evening image"),
                                        new OptionData(OptionType.STRING, "night", "Customize your own night image")
                                ),
                        new SubcommandData("text", "Customize your own texts or quotes")
                                .addOptions(
                                        new OptionData(OptionType.STRING, "morning", "Customize your own morning quote"),
                                        new OptionData(OptionType.STRING, "afternoon", "Customize your own evening quote"),
                                        new OptionData(OptionType.STRING, "night", "Customize your own night quote")
                                )
                ),
                List.of(Permission.MANAGE_SERVER));
    }

    @Override
    public void run(SlashCommandEvent event) {
        if (event.getSubcommandName().equals("channel")) {
            new ChannelSetCommand().run(event);
        } else if (event.getSubcommandName().equals("image")) {
            new ImageSetCommand().run(event);
        } else if (event.getSubcommandName().equals("text")) {
            new TextSetCommand().run(event);
        }
    }
}
