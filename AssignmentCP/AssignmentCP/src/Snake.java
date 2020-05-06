import java.util.ArrayList;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Snake implements Runnable{

	ArrayList<SnakeBody> sb;

	// 0 is West, 1 is North, 2 is East, 3 is South
	int direction;


	public Snake(int x, int y) {
		//start point

		sb = new ArrayList<SnakeBody>();

		for(int i = 0; i < 3; i++) {
			sb.add(new SnakeBody(x + (i*SnakeBody.Swidth),y));
		}

		System.out.println(this.length());

		direction = 3;
	}


	public int length() {
		return this.sb.size();
	}

	public void extendSnake() {

	}

	public void updateSnakeBody() {
		for(int i = this.sb.size()-1; i > 0;i--) {
			// move the body the one index before me
			sb.get(i).move_To(sb.get(i-1));
		}
	}

	public SnakeBody getHead() {
		return sb.get(0);
	}
	
	
	public void changeDirection(int input) {
		// i press down
		if (input == 3) {
			if(direction != 1) {
				direction = input;
			}
		}else if(input == 2) {
			if(direction != 0) {
				direction = input;
			}
		}else if(input == 1) {
			if(direction != 3) {
				direction = input;
			}
		}else if(input == 0) {
			if(direction != 2) {
				direction = input;
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			// To west
			if(direction == 0) {
				updateSnakeBody();
				getHead().x -= 20;
			}
			// To North
			else if(direction == 1) {
				updateSnakeBody();
				getHead().y -= 20;
			}
			// To East

			else if(direction == 2) {
				updateSnakeBody();
				getHead().x += 20;
			}
			// To South
			else {
				updateSnakeBody();
				getHead().y += 20;
			}
			
			try {
				// game speed!
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
