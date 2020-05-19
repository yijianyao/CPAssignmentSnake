import java.util.ArrayList;

public class MPGS {
	 ArrayList<Client> playerList;
	 int simulatedSnakeNum = 10;
	 Client host;
	
	public MPGS() {
		playerList = new ArrayList<Client>();
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
		Game game = new Game();
	}
}
