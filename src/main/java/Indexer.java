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
        	if(stopwords.contains(word) || word.length() == 1){
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
    
    public double jaccardSim(long doc1, long doc2){
        
        StreamItem tweet1 = docIdTweet.get(doc1);
        StreamItem tweet2 = docIdTweet.get(doc2);
        
        Set<String> set1 = tweet1.getWords();
        Set<String> set2 = tweet2.getWords();
        double jsim = (double)intersection(set1,set2)/union(set1,set2);
        
        return jsim;
    } 
    
    public int intersection (Set<String> set1, Set<String> set2) {
		Set<String> a;
		Set<String> b;
		int counter = 0;
		if (set1.size() <= set2.size()) {
			a = set1;
			b = set2; 
		} else {
			a = set2;
			b = set1;
		}
		for (String e : a) {
			if (b.contains(e)) {
				counter++;
			} 
		}
		return counter;
	}
	public HashSet<String> intersectionSet (Set<String> set1, Set<String> set2) {
		Set<String> a;
		Set<String> b;
		HashSet<String> returnSet = new HashSet<String>();
		if (set1.size() <= set2.size()) {
			a = set1;
			b = set2; 
		} else {
			a = set2;
			b = set1;
		}
		for (String e : a) {
			if (b.contains(e)) {
				returnSet.add(e);
			} 
		}
		return returnSet;
	}
	
	public int union (Set<String> set1, Set<String> set2) {
		HashSet<String> returnSet = new HashSet<String>();
		for(String word: set1)
			returnSet.add(word);
		for(String word: set2)
			returnSet.add(word);
		
		return returnSet.size();
		
	}
    
    

}
