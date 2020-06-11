
public class Buffer {
	// Size of the buffer

	private int N ;
	// The buffer is implemented as an array    
	private String[] B;
	// The pointers to the append and take positions     
	private int InPtr = 0, OutPtr = 0;
	// The number of items in the buffer    
	private int Count = 0;    // Constructor takes the size as a parameter    
	public  Buffer(int size) {        
		N = size;
		B = new String[N];
	}


	public synchronized void append(String value) {
		// If the buffer is full we cannot append to it    
		while (Count == N) {
			try {             
				System.out.println("Waiting Order in Append");
				this.wait();     

			} catch (InterruptedException e) { 
			}

		}

		// Place the value in the buffer      
		B[InPtr] = value;
		// print out a debug message      
		System.out.println(Thread.currentThread().getName() +
				" added "+value+" at "+InPtr+" Count was= "+Count);
		// display the state of the buffer for debug purposes      
		// display();
		// increment the pointer. Note the pointer must wrap around to the start      
		InPtr = (InPtr + 1) % N;
		// Update the count      
		Count = Count + 1;
		// If this is the first item added will the consumer thread know?
		this.notifyAll();

	}


	public synchronized String take () {
		while (Count==0) {
			try { 
				System.out.println("Waiting Order in Take");
				wait();
			} catch (InterruptedException e) {}   }
		String I = B[OutPtr];
		System.out.println(Thread.currentThread().getName()+
				" removed "+I+" at "+OutPtr+" Count was = "+Count);
		//display();  
		OutPtr = (OutPtr+1) % N;
		Count = Count-1;
		notifyAll();  
		return I;
	}



	public static void main(String[] args) {
		Buffer buf = new Buffer(5);
		Thread p1 = new Thread(new Producer(buf),"p1");
		Thread c1 = new Thread(new Consumer(buf),"c1");
		Thread p2 = new Thread(new Producer(buf),"p2");
		Thread c2 = new Thread(new Consumer(buf),"c2");
		p1.start();   
		c1.start();     
		p2.start();            
		c2.start();//        }}
	}












}
