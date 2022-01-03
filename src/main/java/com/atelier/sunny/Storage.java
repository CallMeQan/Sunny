package com.atelier.sunny;

import com.atelier.sunny.models.StorageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import net.dv8tion.jda.api.entities.Guild;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Storage {
    private static Path path = new File(".").toPath().resolve("storage.json");
    private static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    private static Logger logger = LogManager.getLogger(Storage.class);

    public static StorageModel json;

    static { // Load into memory
        try {
            FileReader fileReader = new FileReader(path.toFile());
            json = gson.fromJson(fileReader, StorageModel.class);
            if (json == null){
                json = gson.fromJson("{'guild':[]}", StorageModel.class);
            }
            if (json.getGuilds() == null){
                json.setGuilds(new StorageModel.GuildModel[]{}); // Create empty guilds
            }
            logger.info("LOADED json");
        } catch (FileNotFoundException e) {
            createFile();
            logger.warn("Unable to find json file, so created the new one!", e.getCause());
        }
    }

    private static void createFile() {
        try {
            Files.createFile(path);
            FileReader fileReader = new FileReader(path.toFile());
            json = gson.fromJson(fileReader, StorageModel.class);
        } catch (IOException e) {
            logger.fatal(e.getMessage(), e.getCause());
            System.exit(1);
        }
    }

    public static void save() {
        try(Writer writer = new FileWriter(path.toFile())){
            gson.toJson(json, writer);
            writer.close();
        }catch (JsonIOException | IOException e){
            logger.fatal(e.getMessage(), e.getCause());
        }
    }

    public static StorageModel.GuildModel createDefaultGuildModel(Guild guild){
        StorageModel.GuildModel model = new StorageModel.GuildModel();
        model.setId(guild.getId());
        model.setPrefix("");
        model.setChannelId(guild.getDefaultChannel().getId());
        model.setKeepOnLeave(false);
        model.setImJustPing(false);
        return model;
    }

    /**
     * This method for checking all server that bot is in
     * @param guilds
     */
    public void runCheckAll(List<Guild> guilds) {
        for (Guild guild: guilds) {
            if(json.getGuildById(guild.getId()) == null){
                json.addGuild(createDefaultGuildModel(guild));
                logger.warn("Can't find guild with id "+guild.getId()+", attempt to add it to file");
            }
        }
        save();
    }
}
