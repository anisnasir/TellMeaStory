

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads a stream of StreamItems from a file.
 */
public class StreamItemReader {
	private BufferedReader in;

	public StreamItemReader(BufferedReader input) {
		this.in = input;
	}

	public StreamItem nextItem() throws IOException {
		String line = null;
		try {
			line = in.readLine();
		} catch (IOException e) {
			System.err.println("Unable to read from file");
			throw e;
		}

                //System.out.println("StreamItem: " + line);
                
		if (line == null || line.length() == 0)
			return null;

		String[] tokens = line.split("\t");
		if (tokens.length < 2)
			return null;

		long timestamp = Long.parseLong(tokens[0]);
		String[] words = tokens[1].split(" ");
                List<String> list = new LinkedList<String>(Arrays.asList(words));
		return new StreamItem(timestamp, list);
	}
}
