package com.atelier.sunny.utils;

import com.atelier.sunny.models.MongoDBHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

import javax.annotation.Nullable;
import java.util.Arrays;

public class DatabaseUtils {
    private static final MongoClient CLIENT = MongoDBHandler.getClient();
    private static final MongoDatabase DATABASE = CLIENT.getDatabase("Sunny");
    private static final String collName = "SunnyGuilds";

    @Nullable
    public static Document getDocument(String key, Object val) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName);
        return collection.find(Filters.eq(key, val)).first();
    }

    public static void updateDocument(String key, Object val, Document setting) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName);
        deleteDocument(key, val);
        collection.insertOne(setting);
    }

    public static void createDocument(Document... doc) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName);
        collection.insertMany(Arrays.asList(doc));
    }

    public static void deleteDocument(String key, Object val) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName);
        collection.deleteMany(Filters.eq(key, val));
    }

    public static FindIterable<Document> getDocuments(){
        MongoCollection<Document> collection = DATABASE.getCollection(collName);
        return collection.find();
    }
}
