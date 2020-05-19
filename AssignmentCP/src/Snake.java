import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Snake implements Runnable{

	ArrayList<SnakeBody> sb;

	// 0 is West, 1 is North, 2 is East, 3 is South
	int direction;

	Fruit fruit;

	boolean isAlive;

	// because only 1 fruit!!!!
	Semaphore sem = new Semaphore(1);

	// global game speed
	int speed;

	public Snake(int x, int y) {
		//start point

		sb = new ArrayList<SnakeBody>();

		for(int i = 0; i < 3; i++) {
			sb.add(new SnakeBody(x + (i*SnakeBody.Swidth),y));
		}



		direction = 3;

		isAlive = true;

		speed = 400;
	}


	public int length() {
		return this.sb.size();
	}

	public void setFruit(Fruit f) {
		this.fruit = f;
	}

	public void extendSnake() {
		int sblastX = sb.get(sb.size()-1).getX();
		int sblastY = sb.get(sb.size()-1).getY();
		sb.add(new SnakeBody(sblastX,sblastY));
	}

	// let snakebody move
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
		// snake can;t change opposite direction
		// i press down
		//Random r = new Random();
		//input=r.nextInt(3);
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

	public boolean isHitBorder() {

		// hit left
		if(this.getHead().getX() < 0) {
			this.isAlive = false;
			return true;
			// hit right
		}else if(this.getHead().getX() + SnakeBody.Swidth * 2 >= Game.WIDTH ) {
			this.isAlive = false;
			return true;

			// hit top
		}else if(this.getHead().getY() + SnakeBody.Sheight * 2 >= Game.HEIGHT ) {
			this.isAlive = false;
			return true;

		}else if(this.getHead().getY() < 0) {
			this.isAlive = false;
			return true;
			// hit bottom
		}
		else {
			//System.out.println("is hit: " + this.isAlive);
			return false;

		}

	}

	@Override
	public void run() {

		while(isHitBorder() == false ) {
			Random r = new Random();


			int ranDirection = r.nextInt(4);

			//System.out.println("Now is: " + Thread.currentThread().getName());

			// random for bot
			if(!Thread.currentThread().getName().equalsIgnoreCase("0")) {
				// To west
				if(ranDirection == 0) {
					updateSnakeBody();
					getHead().x -= 20;
				}
				// To North
				else if(ranDirection == 1) {
					updateSnakeBody();
					getHead().y -= 20;
				}
				// To East

				else if(ranDirection == 2) {
					updateSnakeBody();
					getHead().x += 20;
				}
				// To South
				else if(ranDirection == 3){
					updateSnakeBody();
					getHead().y += 20;

				}


				// user controlling
			}else {


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

			}



			// set Game speed
			try {
				// game speed!
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			// eat fruit
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.getHead().getX() <= fruit.getX()+fruit.width
					&& this.getHead().getX() + this.getHead().Swidth*2 >= fruit.getX()
					&& this.getHead().getY() <= fruit.getY() + fruit.height
					&& this.getHead().getY() + this.getHead().Sheight*2 >= fruit.getY()) {

				// succfully
				fruit.randomXY();
				this.extendSnake();
				
				decreasedSpeed(50);
				System.out.println("Eat one!!");
			}

			sem.release();


		}


	}


	public synchronized void decreasedSpeed(int s) {
		if(this.speed > 99) {
			this.speed -= s ;
		}
	}

}
