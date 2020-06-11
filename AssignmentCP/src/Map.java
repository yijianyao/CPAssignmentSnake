
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel implements KeyListener{


	Fruit fruit;
	double timer;
	double startTime;
	ArrayList<Snake> snakes;


	JLabel label;
	JLabel scoreLabel;

	JLabel label1;
	JLabel numberOfSnakesLabel;

	JLabel label2;
	JLabel timerLabel;

	JLabel pauseLabel;
	int totalScore;
	int numberOfAvailableSnake;

	public Map() {
		timer = 0;
		this.setBackground(Color.WHITE);
		totalScore = 0;
		
		numberOfAvailableSnake=100;

		init();
		initLabel();

		
		this.add(label);
		this.add(scoreLabel);
		this.add(label1);
		this.add(numberOfSnakesLabel);
		this.add(pauseLabel);
		this.add(label2);
		this.add(timerLabel);
		startTime = System.currentTimeMillis();
	}

	public void init() {
		fruit = new Fruit(0,0,30,30);
		fruit.randomXY();


		snakes = new ArrayList<Snake>();

	}

	public void initLabel() {
		Font font = new Font("Arial", Font.PLAIN, 25);//创建1个字体实例

		this.setLayout(null);
		label = new JLabel("Score: ");
		label.setSize(80, 20);
		label.setLocation(10, 10);
		label.setFont(font);


		scoreLabel = new JLabel("0");
		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(90, 12);
		scoreLabel.setFont(font);


		label1 = new JLabel("Snakes: ");
		label1.setSize(100, 20);
		label1.setLocation(180, 10);
		label1.setFont(font);


		numberOfSnakesLabel = new JLabel("0");
		numberOfSnakesLabel.setSize(100, 20);
		numberOfSnakesLabel.setLocation(280, 12);
		numberOfSnakesLabel.setFont(font);

		pauseLabel = new JLabel("-----------------PAUSE-----------------");
		pauseLabel.setSize(500, 20);
		pauseLabel.setLocation(100, 300);
		pauseLabel.setFont(font);
		pauseLabel.setVisible(false);


		label2 = new JLabel("Time: ");
		label2.setSize(100, 20);
		label2.setLocation(360, 10);
		label2.setFont(font);


		timerLabel = new JLabel("0.0");
		timerLabel.setSize(100, 20);
		timerLabel.setLocation(430, 12);
		timerLabel.setFont(font);



	}

	public synchronized void setSnakes(ArrayList<Snake> snakes) {
		this.snakes = snakes;
	}

	public synchronized void paint(Graphics g){

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
		case KeyEvent.VK_P:
			System.out.println("p");
			System.out.println(snakes.get(0).getIsRun());
			/*
			for(int i = 0 ; i < snakes.size(); i++) {
				if(snakes.get(i).getIsRun() == true) {

					snakes.get(i).setRun(true);

					pauseLabel.setVisible(true);
				}else {
					snakes.get(i).setRun(false);

					pauseLabel.setVisible(false);
				}
			}
			 */
			System.out.println(snakes.get(0).getIsRun());

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

	public void updateLabel() {
		totalScore = 0;
		for(int i = 0 ; i < snakes.size(); i++) {

			totalScore+=snakes.get(i).getScore();

		}



		int count = 0;
		for(int i = 0 ; i < snakes.size(); i++) {
			if(this.snakes.get(i).isAlive == true) {
				count++;
			}
		}

		timer = (System.currentTimeMillis() - this.startTime)/1000;

		DecimalFormat df = new DecimalFormat("#.00");

		numberOfAvailableSnake = count;

		if(numberOfAvailableSnake>0) {



			this.scoreLabel.setText(Integer.toString(totalScore));
			this.numberOfSnakesLabel.setText(Integer.toString(count));
			this.pauseLabel.setText("-----------------PAUSE-----------------");



			String s = df.format(timer);

			this.timerLabel.setText(s);

		}

	}


}
