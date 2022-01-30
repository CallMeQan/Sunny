package com.atelier.sunny.models;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBHandler {
    private static final MongoClientSettings CLIENT_SETTINGS = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(System.getenv("DB_CONNECTION")))
            .applicationName("Sunny")
            .retryWrites(true)
            .retryReads(true)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    private static final MongoClient CLIENT = MongoClients.create(CLIENT_SETTINGS);

    public static synchronized MongoClient getClient() {
        return CLIENT;
    }
}
