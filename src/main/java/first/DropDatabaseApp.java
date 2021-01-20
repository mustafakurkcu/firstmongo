package first;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class DropDatabaseApp {

	public static void main(String[] args) {
		
		MongoClientOptions clientOptions = MongoClientOptions.builder().socketTimeout(2000).build();
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("root", "admin", "example".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), mongoCredential, clientOptions);

        MongoDatabase mydatabase = mongoClient.getDatabase("deneme");
        mydatabase.drop();
        
        mongoClient.close();
	}

}
