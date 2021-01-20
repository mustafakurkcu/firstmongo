package first;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DropRecordApp {

	public static void main(String[] args) {
		
		MongoClientOptions clientOptions = MongoClientOptions.builder().socketTimeout(2000).build();
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root", "admin", "example".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), mongoCredential, clientOptions);

        MongoDatabase mydatabase = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mydatabase.getCollection("user");

        collection.findOneAndDelete(new Document().append("name", "Sinem Hawking"));
        
        mongoClient.close();
	}

}
