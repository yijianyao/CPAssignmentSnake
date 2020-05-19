import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{
	
	final static int WIDTH = 600;
	final static int HEIGHT = 800;
	
	Map panel;
	
	ArrayList<Snake> snakes;
	
	public Game() {

		this.setTitle("Snake");
		this.setSize(WIDTH, HEIGHT);
		panel = new Map();
		panel.setSize(WIDTH, HEIGHT);
	
		
		
		
		this.add(panel);
		this.addKeyListener(panel);
		
		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initSnakes(int numberOfSnakes) {
		snakes = new ArrayList<Snake>();
		
		Random r = new Random();
	
		for(int i = 0 ; i < numberOfSnakes; i++) {	
			int newX = r.nextInt(150) + 250;
			int newY = r.nextInt(150) + 250;
			snakes.add(new Snake(newX,newY));
			
			Thread snakeThread = new Thread(snakes.get(i),Integer.toString(i));
			//snakeThread.setName("sss");
			
			
			snakeThread.start();
			
			snakes.get(i).setFruit(panel.fruit);
			
			
		}
		
		this.panel.setSnakes(snakes);
	}
	
	public static void main(String[] args) {
		Game m = new Game();
		
		m.initSnakes(3);
		
		m.panel.fruit.randomXY();
		
		
		//Thread t1 = new Thread(m.panel.snake);
		
		//Thread t2 = new Thread(m.panel.snake);
		
		//t1.start();
		//t2.start();
		
		while(true) {
			m.panel.repaint();
			//Thread.sleep(500);
		}
	}

}
