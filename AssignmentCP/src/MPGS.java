import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MPGS implements Runnable{
	ArrayList<Client> playerList;
	int simulatedSnakeNum = 10;
	Client host;
	Login log;
	Game game;
	Buffer buffer;
	boolean numSnakeSelect;




	JLabel label1;
	JTextField numberTextbox;

	public MPGS(Buffer buffer) {
		numSnakeSelect = false;
		Login log = new Login(buffer);

		Thread p1 = new Thread(log,"login");
		p1.start();
		
		playerList = new ArrayList<Client>();

		// let log finish
		while(log.pass == false) {
			numSnakeSelect = false;
			//startGame();
			System.out.println("");
		}
		numSnakeSelect = true;
		//run();
		//Client tem = new Client(log.nameTextbox.getText());
		//buffer.take();

		startGame();
		//System.out.println("Love");
	}

	public void addPlayer(Client client) {
		if(host == null) {
			host = client;
		}
		playerList.add(client);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Buffer take times");
		int i = 0;
		while(numSnakeSelect == true) {
			//	for (int i = 0; i < 5; i++) {
			System.out.println("Buffer take " + i + " times");
			buffer.take();
			//delay();
			//	}
			i++;
		}
	}

	public void startGame() {

		// select how many snakes
		String a = JOptionPane.showInputDialog(null," Number of Snakes£º\n","Snakes",JOptionPane.PLAIN_MESSAGE);

		int numberofSna = Integer.valueOf(a);



		//only host can trigger.
		//jump to game page

		game = new Game();

		game.initMap();
		// change the number of snake
		game.initSnakes(numberofSna);

		game.panel.fruit.randomXY();


		//Thread t1 = new Thread(m.panel.snake);

		//Thread t2 = new Thread(m.panel.snake);

		//t1.start();
		//t2.start();

		// only run when game has snakes
		while(game.panel.numberOfAvailableSnake > 0) {
			game.panel.repaint();
			game.panel.updateLabel();
			//Thread.sleep(500);
		}

		int n = JOptionPane.showConfirmDialog(null, "Game Over! \n Do you want to restart?", "Snake",JOptionPane.YES_NO_OPTION); 

		if(n == 0) {
			game.dispose();
			startGame();
		}



		//out put to txt file
		PrintWriter writer;
		try {
			writer = new PrintWriter("result.txt", "UTF-8");
			writer.println("\n---------------------------------------------");
			writer.println("User: " + log.nameTextbox.getText());
			writer.println("\nDate and Time: " + java.time.LocalDateTime.now());
			writer.println("\nNumber of Snakes: " + numberofSna);
			writer.println("\nScores: " + game.panel.totalScore);
			writer.println("\nRunning Time: " + game.panel.timer);
			writer.println("---------------------------------------------");
			writer.close();




			JOptionPane.showMessageDialog(null, "Game summary has been exported to file 'result.txt'", "Snakes",JOptionPane.PLAIN_MESSAGE);


		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		game.dispose();
		//JOptionPane.showMessageDialog(null, "Game Over", "Snakes",JOptionPane.ERROR_MESSAGE);



	}


	public static void main(String[] args) {
		Buffer b = new Buffer(4);
		MPGS g = new MPGS(b);


		Thread c1 = new Thread(g,"main");
		c1.start();
		//g.startGame();
	}


}
