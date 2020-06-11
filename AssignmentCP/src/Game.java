import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JFrame{
	
	final static int WIDTH = 600;
	final static int HEIGHT = 800;
	
	

	Map panel;
	
	ArrayList<Snake> snakes;
	
	public Game() {

		this.setTitle("Snake");
		this.setSize(WIDTH, HEIGHT);

		

		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initMap() {
		panel = new Map();
		panel.setSize(WIDTH, HEIGHT);
		this.add(panel);
		this.addKeyListener(panel);
	}
	
	public synchronized void initSnakes(int numberOfSnakes) {
		snakes = new ArrayList<Snake>();
		
		Random r = new Random();
		int numberOfRow = WIDTH/20 - 1;
		int numberOfCol = HEIGHT/20 - 1;
		
		/*snakes.add(new Snake(300,200));
		
		Thread snakeThread = new Thread(snakes.get(0),Integer.toString(0));
		//snakeThread.setName("sss");
		//System.out.println("snake is: " + snakeThread.getName());
		
		snakeThread.start();*/
		
		//snake new location
		for(int i = 0 ; i < numberOfSnakes; i++) {	
			int newX = r.nextInt(numberOfRow) * 20;
			int newY = r.nextInt(numberOfCol) * 20;
			snakes.add(new Snake(newX,newY));
			
			Thread snakeThread = new Thread(snakes.get(i),Integer.toString(i));
			//snakeThread.setName("sss");
			//System.out.println("snake is: " + snakeThread.getName());
			
			snakeThread.start();
			
			snakes.get(i).setFruit(panel.fruit);
			
			
		}
		
		this.panel.setSnakes(snakes);
	}

}
