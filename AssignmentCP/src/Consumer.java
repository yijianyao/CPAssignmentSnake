
public class Consumer implements Runnable {
	private Buffer buffer;
	public Consumer(Buffer c) {
		buffer = c; }
	public void run() {
		for (int i = 0; i < 40; i++) {
			buffer.take();
			//delay();
		}

	}}