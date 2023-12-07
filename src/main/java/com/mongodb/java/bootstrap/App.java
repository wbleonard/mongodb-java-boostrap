package com.mongodb.java.bootstrap;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.internal.build.MongoDriverVersion;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App {

    public static void main(String[] args) {
        System.out.println("\n*** MongoDB Java Bootstrap ***");

        String mongoDriverVersion = MongoDriverVersion.VERSION;
        System.out.println("\n*** MongoDB Driver Version: " + mongoDriverVersion + " ***");

        String connStyle = System.getenv("mongodb_connection_style");
        // System.out.println( "mongodb_connection_style: " + connStyle);

        String uriString = null;
        String serverName = null;
        int port = 27017;
        String username = null;
        String password = null;

        if (connStyle != null && connStyle.equals("ServerAddress")) {
            System.out.println("\n*** Using ServerAddress connection style ***\n");
            serverName = System.getenv("server");
            port = Integer.parseInt(System.getenv("port"));
            username = System.getenv("username");
            password = System.getenv("password");
        } else {
            System.out.println("\n*** Using URI connection style ***\n");
            // Grab the connection string from the environment:
            uriString = System.getenv("mongodb_uri");

            if (uriString == null) {
                System.out.println("MongoDB Connection String environment variable, 'mongodb_uri', is not set");
                return;
            }
        }

        // String uriString = "mongodb://localhost:27017";
        String database_string = "ai-1ops";
        String collection_string = "jira";
        MongoDatabase database = null;

        if (connStyle == null || connStyle.equals("URI")) {
            com.mongodb.client.MongoClient mongoClient = MongoClients.create(uriString);
            database = mongoClient.getDatabase(database_string);
        } else {

            ServerAddress serverAddress = new ServerAddress(serverName, port);
            List<ServerAddress> addressList = Arrays.asList(new ServerAddress[] { serverAddress });
            System.out.println("addressList: " + addressList);

            List<MongoCredential> mongoCredentialList = new ArrayList();
            MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(username, "admin",
                    password.toCharArray());

            // MongoClientOptions.Builder options = new MongoClientOptions.Builder();
            // options.sslEnabled(true);

            MongoClient mongoClient = MongoClients
                    .create(MongoClientSettings.builder()
                            .applyToClusterSettings(
                                    builder -> builder.hosts(addressList))
                            .build());

            database = mongoClient.getDatabase(database_string);

        }

        MongoCollection<Document> collection = database.getCollection(collection_string);
        Document myDoc = collection.find().first();

        System.out.println("\nResults: ");
        System.out.println("\n" + myDoc + "\n");
    }
}
