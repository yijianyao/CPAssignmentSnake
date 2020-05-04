
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel{


	Fruit fruit;
	float timer;

	public Map() {
		timer = 0;
		this.setBackground(Color.WHITE);
		init();
	}

	public void init() {
		fruit = new Fruit(0,0,40,40);
		fruit.randomXY();
	}

	public void paint(Graphics g){
			
		super.paint(g);
		g.drawImage(fruit.getImg(), fruit.getX(),fruit.getY(),40,40,null);
		
	}




}
