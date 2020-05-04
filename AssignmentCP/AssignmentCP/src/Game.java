import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{
	
	final static int WIDTH = 600;
	final static int HEIGHT = 800;
	Map panel;
	public Game() {

		this.setTitle("Snake");
		this.setSize(WIDTH, HEIGHT);
		panel = new Map();
		panel.setSize(WIDTH, HEIGHT);
	
		
		
		
		this.add(panel);
		
		
		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Game m = new Game();
		
		while(true) {
			try {
				m.panel.fruit.randomXY();
				m.panel.repaint();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
