import java.util.ArrayList;

public class MPGS {
	ArrayList<Client> playerList;
	int simulatedSnakeNum = 10;
	Client host;
	Login log;
	Game game;

	public MPGS() {
	//	log = new Login();
		playerList = new ArrayList<Client>();


		//if(log.pass == true) {
			startGame();
		//}
	}

	public void addPlayer(Client client) {
		if(host == null) {
			host = client;
		}
		playerList.add(client);
	}

	public void startGame() {
		//only host can trigger.
		//jump to game page

		game = new Game();


		// change the number of snake
		game.initSnakes(50);

		game.panel.fruit.randomXY();


		//Thread t1 = new Thread(m.panel.snake);

		//Thread t2 = new Thread(m.panel.snake);

		//t1.start();
		//t2.start();

		while(true) {
			game.panel.repaint();
			game.panel.updateLabel();
			//Thread.sleep(500);
		}



	}


	public static void main(String[] args) {
		MPGS g = new MPGS();
		//g.startGame();
	}
}
