package first;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;

public class InsertRecordApp {

	public static void main(String[] args) {
		
		MongoClientOptions clientOptions = MongoClientOptions.builder().socketTimeout(2000).build();
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root", "admin", "example".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), mongoCredential, clientOptions);

        MongoDatabase mydatabase = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mydatabase.getCollection("user");

        Document obj=new Document()
                .append("name","Sinem Hawking")
                .append("date","1982")
                .append("country","England")
                .append("denemeklon", true);
		
        InsertOneOptions insertOneOptions = new InsertOneOptions();
        insertOneOptions.bypassDocumentValidation(false);
        collection.insertOne(obj, insertOneOptions);
        
        Document obj2=new Document()
                .append("name","Isaac Newton")
                .append("date","1643")
                .append("country","England");
        
        collection.insertOne(obj2, insertOneOptions);
        
        System.out.println(mydatabase.getCollection("user"));

        mongoClient.close();
	}

}
