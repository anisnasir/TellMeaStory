import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Indexer {
    long lastDocID = 0;
    public HashMap<Long, StreamItem> docIdTweet = new HashMap<Long, StreamItem> ();
    StopWords stopwords;
    
    public Indexer (StopWords swlist){
        stopwords = swlist;
    }
    
    private long generateDocID() {
		return lastDocID++;
    }
    
    long addItem(StreamItem item ) { 
    	long id = generateDocID();
        
        //remove stopwords from tweet before addind it to the indexer
    	HashSet<String> words = new HashSet<String>(item.getWords());
        for(String word: words) {
        	//if(!word.startsWith("#") || word.length() <= 1){
        	if(stopwords.contains(word) || word.length() <=1 || word.equals("RT")) {
        		item.remove(word);
        	}
        }
    	docIdTweet.put(id, item);
    	return id;
    }
    
    StreamItem removeItem(long id) {
    	StreamItem temp = docIdTweet.get(id);
    	docIdTweet.remove(id);
    	return temp;
    }
    
    public int size(){
        return docIdTweet.size();
    }
    
    
}
