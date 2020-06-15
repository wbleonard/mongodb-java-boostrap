package com.mongodb.java.bootstrap;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class App {

    public static void main(String[] args) {
        System.out.println("MongoDB Java Bootstrap");

        // Grab the connection string from the environment:
        String uriString = System.getenv("mongodb_uri");
        
        if (uriString == null) {
            System.out.println("MongoDB Connection String environment variable, 'mongodb_uri', is not set");
            return;
        }

        //String uriString = "mongodb://localhost:27017";
        String database_string = "sample_analytics";
        String collection_string = "customers";

        MongoClient mongoClient = MongoClients.create(uriString);
        MongoDatabase database = mongoClient.getDatabase(database_string);
        MongoCollection<Document> collection = database.getCollection(collection_string);
        Document myDoc = collection.find().first();
        System.out.println(myDoc);
    }
}
