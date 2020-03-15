import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Summarizer {
	
	void getSummary(HashSet<Long> tweetIds, Indexer indexer, int size) {
		HashMap<Long, Integer> summary = new HashMap<Long,Integer>();
		System.out.println(tweetIds);
		for(Long id:tweetIds) {
			Long key = (long) 0;
			for(Long tweet: summary.keySet()) {
				if(jaccardSim(id,tweet,indexer) > 1)
				{
					key = tweet;
					break;
				}
			}
			if(key >0) {
				int weight = summary.get(key);
				summary.put(key, weight+1);
			}else if (summary.size() < size) {
				summary.put(id,1);
			}else {
				int weight = removeMin(summary);
				summary.put(id,1);
			}
		}
		System.out.println(summary);
		int i = 0;
		for(Long id: summary.keySet()) {
			System.out.println(i++ + " " + id + " " + indexer.docIdTweet.get(id).tweet);
		}
	}
	int removeMin(HashMap<Long, Integer> summary) {
		Long min = (long) 0;
		int minWeight = 0;
		for(Long key: summary.keySet()) {
			if(min == 0) {
				min = key;
				minWeight = summary.get(key);
			}else if(minWeight < summary.get(key)) {
				min = key;
				minWeight = summary.get(key);
			}
		}
		int retweight = summary.get(min);
		summary.remove(min);
		return retweight;
	}
	
public double jaccardSim(long doc1, long doc2, Indexer indexer){
        
        StreamItem tweet1 = indexer.docIdTweet.get(doc1);
        StreamItem tweet2 = indexer.docIdTweet.get(doc2);
        
        Set<String> set1 = tweet1.getWords();
        Set<String> set2 = tweet2.getWords();
        double intersection = intersection(set1,set2);
        double jsim = (double)intersection/(set1.size() + set2.size()-intersection);
        
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
	
}
