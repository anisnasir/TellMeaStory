import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;


public class Main {
	public static void main (String args [] ) {
		//Declare the file
		String inFileName = "/Users/amirasoliman/NetBeansProjects/TellMeaStory/src/tweets/test.txt";
                String inFileNameStopW = "/Users/amirasoliman/NetBeansProjects/TellMeaStory/src/tweets/stopwords.txt";
		
		
		
		//Initialize HashedIndex
		HashedIndex index = new HashedIndex();
		
		//Initialize the slidingWindow
		int windowSize = 100;//10000;
		FixedSizeSlidingWindow slidingWindow = new FixedSizeSlidingWindow(windowSize);
		
		// Open the input file
		BufferedReader in = null;
                BufferedReader inSW = null;
        
		try {
            InputStream rawin = new FileInputStream(inFileName);
            InputStream swin = new FileInputStream(inFileNameStopW);
            
            if (inFileName.endsWith(".gz"))
                rawin = new GZIPInputStream(rawin);
            
            in = new BufferedReader(new InputStreamReader(rawin));
            inSW = new BufferedReader(new InputStreamReader(swin));
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            System.exit(1);
        } catch(Exception ex) {
        	
        }
        //System.out.println("file opend");
        StreamItemReader reader = new StreamItemReader(in);
        StopWords stopWords = new StopWords(inSW);
        stopWords.fillList();
       
        //Declare Indexer
        Indexer indexer = new Indexer(stopWords);
        
        try {
                        //System.out.println("reading items");
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
                                                //check that with Anis it's remove not insert (index.insert(word, docID);)
						index.remove(word, deletedItem);
                                                indexer.removeItem(deletedItem);
                                    }
				}
				
				item = reader.nextItem();
			}
        } catch( Exception ex) { 
        	ex.printStackTrace();
        }
        
        
         for(long i=indexer.lastDocID -1, loopcount=0; loopcount <windowSize-2 ; i--, loopcount++){
             System.out.println("jaccard sim between t:" + i + " and t" + (i-1) +" = " + indexer.jaccardSim(i, i-1));
         }
        
	}
}
