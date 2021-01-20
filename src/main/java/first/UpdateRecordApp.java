package first;


import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

public class UpdateRecordApp {

	public static void main(String[] args) {
		
		MongoClientOptions clientOptions = MongoClientOptions.builder().socketTimeout(2000).build();
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root", "admin", "example".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), mongoCredential, clientOptions);

        MongoDatabase mydatabase = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mydatabase.getCollection("user");

        Bson filterObj = new Document()
                .append("name","Martin Hawking");
        
        Bson updatedObj = new Document("$set", new Document()
                .append("name","Mehmet Hawking")
                .append("date","1986")
                .append("country","Turkey")
                .append("isityou", "NO"));
        
        FindOneAndUpdateOptions opts = new FindOneAndUpdateOptions();
        opts.upsert(true);
        opts.returnDocument(ReturnDocument.AFTER);
        Document updatedDocument = collection.findOneAndUpdate(filterObj, updatedObj, opts);
        System.out.println(updatedDocument.toJson());
        
        mongoClient.close();
	}

}
