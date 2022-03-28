package com.atelier.sunny.utils;

import com.atelier.sunny.models.MongoDBHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class DatabaseUtils {
    private static final MongoClient CLIENT = MongoDBHandler.getClient();
    private static final MongoDatabase DATABASE = CLIENT.getDatabase("Sunny");

    public static Document getDocument(String key, Object val, @NotNull CollName collName) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName.getName());
        return collection.find(Filters.eq(key, val)).first();
    }

    public static void updateDocument(String key, Object val, Document setting, @NotNull CollName collName) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName.getName());
        deleteDocument(key, val, collName);
        collection.insertOne(setting);
    }

    public static void createDocument(@NotNull CollName collName, Document... doc) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName.getName());
        collection.insertMany(Arrays.asList(doc));
    }

    public static void deleteDocument(String key, Object val, @NotNull CollName collName) {
        MongoCollection<Document> collection = DATABASE.getCollection(collName.getName());
        collection.deleteMany(Filters.eq(key, val));
    }

    public static FindIterable<Document> getDocuments(@NotNull CollName collName){
        MongoCollection<Document> collection = DATABASE.getCollection(collName.getName());
        return collection.find();
    }

    public enum CollName{
        OSU("SunnyOsu"),
        GUILD("SunnyGuilds");
        private String name;
        CollName(String name) {
            this.name = name;
        }
        public String getName(){return this.name;}
    }
}
