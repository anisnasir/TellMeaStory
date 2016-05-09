import java.util.HashMap;


public class Indexer {
    private long lastDocID = 0;
    HashMap<Long, StreamItem> docIdTweet = new HashMap<Long, StreamItem> ();
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
        for(int i =0 ;i< item.getWordsSize();i++) {
            if(stopwords.contains(item.getWord(i))){
                item.deleteWordAt(i);
                i--;
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
    
    public double jaccardSim(long doc1, long doc2){
        double jsim = 0.0;
        
        StreamItem tweet1 = docIdTweet.get(doc1);
        StreamItem tweet2 = docIdTweet.get(doc2);
        
        for(int i =0 ;i< tweet1.getWordsSize();i++) {
            jsim += tweet2.containsWord(tweet1.getWord(i));
        }
        
        return jsim/(tweet1.getWordsSize()+tweet2.getWordsSize());
    } 
    
    

}
