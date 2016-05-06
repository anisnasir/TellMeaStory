import java.util.HashMap;


public class Indexer {
    private long lastDocID = 0;
    HashMap<Long, StreamItem> docIdTweet = new HashMap<Long, StreamItem> ();
    
    private long generateDocID() {
		return lastDocID++;
    }
    
    long addItem(StreamItem item ) { 
    	long id = generateDocID();
    	docIdTweet.put(id, item);
    	return id;
    }
    
    StreamItem removeItem(long id) {
    	StreamItem temp = docIdTweet.get(id);
    	docIdTweet.remove(id);
    	return temp;
    }
    
    

}
