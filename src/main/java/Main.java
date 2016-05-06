import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;


public class Main {
	public static void main (String args [] ) {
		//Declare the file
		String inFileName = "/Users/anis/Datasets/yahoo_input/twitter";
		
		//Declare Indexer
		Indexer indexer = new Indexer();
		
		//Initialize HashedIndex
		HashedIndex index = new HashedIndex();
		
		//Initialize the slidingWindow
		int windowSize = 10000;
		FixedSizeSlidingWindow slidingWindow = new FixedSizeSlidingWindow(windowSize);
		
		// Open the input file
		BufferedReader in = null;
        
		try {
            InputStream rawin = new FileInputStream(inFileName);
            if (inFileName.endsWith(".gz"))
                rawin = new GZIPInputStream(rawin);
            in = new BufferedReader(new InputStreamReader(rawin));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
            System.exit(1);
        } catch(Exception ex) {
        	
        }
      
        StreamItemReader reader = new StreamItemReader(in);
        
        try {
	       
			StreamItem item = reader.nextItem();
			
			while(item != null) {
				long docID = indexer.addItem(item);
				for(int i =0 ;i< item.getWordsSize();i++) {
					String word = item.getWord(i);
					index.insert(word, docID);
				}
				
				
				
				long deletedItem = slidingWindow.add(docID);
				
				if(deletedItem >= 0 )
				{
					StreamItem removedItem = indexer.removeItem(deletedItem);
					for(int i =0 ;i< removedItem.getWordsSize();i++) {
						String word = removedItem.getWord(i);
						index.insert(word, docID);
					}
				}
				
				item = reader.nextItem();
			}
        } catch( Exception ex) { 
        	ex.printStackTrace();
        }
	}
}
