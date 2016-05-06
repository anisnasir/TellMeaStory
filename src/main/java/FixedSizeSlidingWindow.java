
import java.util.LinkedList;


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
