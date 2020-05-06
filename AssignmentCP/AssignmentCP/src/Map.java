
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel implements KeyListener{


	Fruit fruit;
	float timer;

	Snake snake;

	public Map() {
		timer = 0;
		this.setBackground(Color.WHITE);
		
		init();
	}

	public void init() {
		fruit = new Fruit(0,0,20,20);
		fruit.randomXY();


		snake = new Snake(50,100);
	}

	public void paint(Graphics g){

		super.paint(g);
		g.drawImage(fruit.getImg(), fruit.getX(),fruit.getY(),20,20,null);

		// draw snake
		for( int i = 0 ; i < snake.length(); i++) {

			System.out.println(snake.sb.get(i));
			g.drawImage(snake.sb.get(i).getImg(), 
					snake.sb.get(i).getX(),
					snake.sb.get(i).getY(),20,20,null);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// change direction for snake
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_UP:
			System.out.println("up");
			snake.changeDirection(1);
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("down");
			snake.changeDirection(3);
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("left");
			snake.changeDirection(0);
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("right");
			snake.changeDirection(2);
			break;
		default:
			// Unsup
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}




}
