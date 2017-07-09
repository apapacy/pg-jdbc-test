package pg;

/**
 * Created by apapacy on 7/9/17.
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mongo {
    public static void main(String[] args){
        for (int i = 0; i < 100; i++)
            (new Worker(i)).start();
        System.out.println("OK");
    }
}
class Worker extends Thread {
    int i;
    Worker(int i) {
        this.i = i;
    }
    public void run() {
        MongoClient mongoClient = new MongoClient( "localhost" );
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("test");
        Document document;
        for (int i = 0; i < 1000; i++) {
            document = new Document("name", "Харьков - " + this.i + " - " + i);
            collection.insertOne(document);
        }
    }
}
