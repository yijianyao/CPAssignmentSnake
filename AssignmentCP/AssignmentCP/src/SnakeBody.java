import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SnakeBody {
	int x = 0;
	int y = 0;
	final static int Swidth = 20;
	final static int Sheight = 20;
	BufferedImage img;
	
	public SnakeBody(int x, int y) {
		this.x = x;
		this.y = y;
		try {
			img = ImageIO.read(new File("snakebody.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void move_To (SnakeBody to) {
		this.x = to.x;
		this.y = to.y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public BufferedImage getImg() {
		return this.img;
	}
	
	

	public String toString() {
		return "x: " + this.x + " y: " + this.y;
	}
}
