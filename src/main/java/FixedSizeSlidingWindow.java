
import java.util.LinkedList;

/*
 * this class contains the sliding window and drops the ids of a tweet when the window gets full
 */
public class FixedSizeSlidingWindow {
	
	public int windowSize;
	LinkedList<Long> fifo;
	FixedSizeSlidingWindow() {
		windowSize = 0;
		fifo = new LinkedList<Long>();
	}
	FixedSizeSlidingWindow(int wSize) {
		windowSize = wSize;
		fifo = new LinkedList<Long>();
	}
	
	long add(Long newEdge) {
		fifo.addLast(newEdge);
		if(fifo.size() >=windowSize) {
			long returnEdge = fifo.removeFirst();
			return returnEdge;
		}else {
			return -1;
		}
	}
}
