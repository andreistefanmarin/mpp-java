package eu.ase.nosql;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBClient {
    public static void main(String[] args) {
        try(MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase db = mongoClient.getDatabase("test");
            System.out.println("Connected to db successfully");

            if(db.getCollection("mycol") != null) {
                db.getCollection("mycol").drop();
            }
            db.createCollection("mycol");
            System.out.println("Collection created successfully");
            MongoCollection<Document> coll = db.getCollection("mycol");
            System.out.println("Collection selected successfully");

            Document doc = new Document("title", "MongoDB")
                    .append("description", "database")
                    .append("likes", 100)
                    .append("url", "http://localhost:27017")
                    .append("by", "MongoDB");
            coll.insertOne(doc);
            System.out.println("Document inserted successfully");

            FindIterable<Document> iterableFind = coll.find();
            MongoCursor<Document> cursor = iterableFind.iterator();

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            cursor.close();
        }
    }
}
