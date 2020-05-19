
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel implements KeyListener{


	Fruit fruit;
	float timer;

	ArrayList<Snake> snakes;
	


	public Map() {
		timer = 0;
		this.setBackground(Color.WHITE);

		init();
	}

	public void init() {
		fruit = new Fruit(0,0,20,20);
		fruit.randomXY();


		snakes = new ArrayList<Snake>();

	}

	public void setSnakes(ArrayList<Snake> snakes) {
		this.snakes = snakes;
	}

	public void paint(Graphics g){

		super.paint(g);
		g.drawImage(fruit.getImg(), fruit.getX(),fruit.getY(),20,20,null);

		// draw snake
		for( int i = 0 ; i < snakes.size(); i++) {

			//System.out.println(snakes.get(i).sb.get(i));
			// draw each snakes
			for(int index = 0 ;  index < snakes.get(i).length(); index++) {

				if(snakes.get(i).isAlive == true) {
					g.drawImage(snakes.get(i).sb.get(index).getImg(), 
							snakes.get(i).sb.get(index).getX(),
							snakes.get(i).sb.get(index).getY(),20,20,null);

				}
			}
		}
	}

	/*
	public void paintSnake(Graphics g, Snake s){

		this.repaint();

		// draw snake
		for( int i = 0 ; i < snakes.size(); i++) {

			//System.out.println(snakes.get(i).sb.get(i));
			// draw each snakes
			for(int index = 0 ;  index < snakes.get(i).length(); index++) {

			g.drawImage(snakes.get(i).sb.get(index).getImg(), 
					snakes.get(i).sb.get(index).getX(),
					snakes.get(i).sb.get(index).getY(),20,20,null);

			}
		}

	}*/

	@Override
	public void keyPressed(KeyEvent e) {
		// change direction for snake
		int code = e.getKeyCode();

		// only control first snakes
		switch (code) {
		case KeyEvent.VK_UP:
			System.out.println("up");
			snakes.get(0).changeDirection(1);
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("down");
			snakes.get(0).changeDirection(3);
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("left");
			snakes.get(0).changeDirection(0);
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("right");
			snakes.get(0).changeDirection(2);
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
