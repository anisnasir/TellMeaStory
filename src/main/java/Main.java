import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;


public class Main {
	public static void main (String args [] ) {
		//Declare the file
		String inFileName = "/Users/anis/Desktop/tweets/tweets.txt";
		String inFileNameStopW = "./stopwords.txt";
		
		
		//Initialize HashedIndex
		HashedIndex index = new HashedIndex();

		//Initialize the slidingWindow
		int windowSize = 100000;//10000;
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
				
				for(String word: item.getWords()) {
					index.insert(word, docID);
				}



				long deletedItem = slidingWindow.add(docID);
				if(deletedItem >= 0 )
				{
					StreamItem removedItem = indexer.removeItem(deletedItem);
					
					for(String word: removedItem.getWords()) {
						//check that with Anis it's remove not insert (index.insert(word, docID);)
						index.remove(word, deletedItem);
					}
				}
				item = reader.nextItem();
			}
		} catch( Exception ex) { 
			ex.printStackTrace();
		}
		long count = 0; 
		long sum = 0;
		int counter = 0;
		for (String word:index.index.keySet()) {
			//System.out.println(index.index.get(word).size());
			sum+=index.index.get(word).size();
			count++;
			//if( index.index.get(word).size() > 400)
				//System.out.println(word);
		}
		//System.out.println(sum/count);
		//System.out.println(counter);
		//System.out.println(indexer.docIdTweet.get(484));
		Summarizer temp = new Summarizer();
		temp.getSummary(index.index.get("#Game7"), indexer,100);

	}
}
