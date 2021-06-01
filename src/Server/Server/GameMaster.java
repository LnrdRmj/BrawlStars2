package Server.Server;

import java.util.Vector;

public class GameMaster implements Runnable{

	private Vector<PlayerServerThread> players;
	
	public GameMaster() {
		
		players = new Vector<PlayerServerThread>();
		
	}
	
	public void addPlayerThread(PlayerServerThread player) {
		
		this.players.add(player);
		
	}

	@Override
	public void run() {

		while(true) {
			
			for(PlayerServerThread player : players) {
				
				sendToAllBut(player, player.getInfo());
				
			}
			
		}
		
	}
	
	public void sendToAllBut(PlayerServerThread player, String info) {
		
		for(PlayerServerThread pl : players) {
			
			if (pl != player) {
				
				pl.write(info);
				
			}
			
		}
		
	}

	public int numPlayers() {
		return players.size();
	}
	
}
