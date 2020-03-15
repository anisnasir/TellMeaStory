/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   First version:  Johan Boye, 2010
 *   Second version: Johan Boye, 2012
 *   Additions: Hedvig Kjellström, 2012-14
 */  

import java.util.HashMap;
import java.util.HashSet;
import com.clearspring.analytics.stream.StreamSummary;


/**
 *   Implements an inverted index as a Hashtable from words to PostingsLists.
 */
public class HashedIndex {
	public  HashMap<String,HashSet<Long>> index;
	
	HashedIndex() {
		index = new HashMap<String,HashSet<Long>>();
		
	}
	public void insert( String token, long docID) {	
		if(index.containsKey(token)) {
			index.get(token).add(docID);

		}else {
			HashSet<Long> temp = new HashSet<Long>();
			temp.add(docID);
			index.put(token, temp);
		}	
	}	
	
	public void remove( String token, long docID) {
		if(index.containsKey(token)) {
			index.get(token).remove(docID);
			if(index.get(token).size() == 0)
				index.remove(token);
		}
	}
	
}
