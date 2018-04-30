package com.mongodb.java.bootstrap;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.net.UnknownHostException;

public class App {

    public static void main(String[] args) {
        System.out.println("MongoDB Java Boostrap");

        String uriString = "mongodb://localhost:27017";

        MongoClientURI uri = new MongoClientURI(uriString);
        MongoClient mongoClient;
		try {
            mongoClient = new MongoClient(uri);
            DB db = mongoClient.getDB("restaurants");
            DBCollection coll = db.getCollection("restaurants");
            DBObject myDoc = coll.findOne();
            System.out.println(myDoc);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }
}

