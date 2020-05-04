import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fruit {

	int x;
	int y;
	BufferedImage img;
	int width;
	int height;
	Random ran;
	
	public Fruit(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		ran = new Random();
		try {
			this.img = ImageIO.read(new File("fruit.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public BufferedImage paintFrut(int x, int y) {
		return this.img;
	}
	
	
	public void randomXY() {
		this.x = ran.nextInt(Game.WIDTH-this.width);
		this.y = ran.nextInt(Game.HEIGHT-this.height);
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
		return img;
	}


	public void setImg(BufferedImage img) {
		this.img = img;
	}
	
	
	
}
