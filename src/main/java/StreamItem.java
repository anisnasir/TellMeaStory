

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * An atomic item in the stream. An item is composed by a timestamp and a line
 * of text. The timestamp represents the position in the stream, the text is the
 * payload and is represented as list of strings.
 */
public class StreamItem implements Iterable<String> {
	private long timestamp;
	private HashSet<String> words;
	

	StreamItem(long timestamp, HashSet<String> words) {
		this.timestamp = timestamp;
		this.words = words;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getWordsSize() {
		return words.size();
	}
	
	public HashSet<String> getWords(){
		return words;
	}

	public Iterator<String> iterator() {
		return words.iterator();
	}
        
        public int containsWord(String word){
            if(words.contains(word))
                return 1;
            
            return 0;
        }

	@Override
	public String toString() {
		return words.toString();
	}
	
	public void remove(String word) {
		words.remove(word);
	}
}
