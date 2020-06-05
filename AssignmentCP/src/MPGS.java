import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MPGS{
	ArrayList<Client> playerList;
	int simulatedSnakeNum = 10;
	Client host;
	Login log;
	Game game;
	
	
	
	
	JLabel label1;
	JTextField numberTextbox;

	public MPGS() {
		log = new Login();
		playerList = new ArrayList<Client>();

		// let log finish
		while(log.pass == false) {
			//startGame();
			System.out.println("");
		}
		startGame();
		//System.out.println("Love");
	}

	public void addPlayer(Client client) {
		if(host == null) {
			host = client;
		}
		playerList.add(client);
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
		MPGS g = new MPGS();
		//g.startGame();
	}
}
