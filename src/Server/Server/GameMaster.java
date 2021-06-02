package Server.Server;

import java.util.Vector;

public class GameMaster implements Runnable{

	private Vector<PlayerServerThread> players;
	private Vector<PlayerServerThread> playersToAdd;
	private Vector<PlayerServerThread> playersToRemove;
	
	public GameMaster() {
		
		players = new Vector<PlayerServerThread>();
		playersToAdd = new Vector<PlayerServerThread>();
		playersToRemove = new Vector<PlayerServerThread>();
		new Thread(this).start();
		
	}
	
	public void addPlayerThread(PlayerServerThread player) {
		
		this.playersToAdd.add(player);
		
	}

	@Override
	public void run() {

		while(true) {
			
			for(PlayerServerThread player : players) {
				
				// Quando il socket del player viene chiuso o disconnesso per qualche motivo
				if (player.isClosed()) {
					
					playersToRemove.add(player);
					continue;
					
				}
				
				sendToAllBut(player, player.getInfo());
				
			}
			
			if (playersToAdd.size() > 0) {
				
				players.addAll(playersToAdd);
				playersToAdd.clear();
				
			}
			
			if (playersToRemove.size() > 0) {
				
				players.removeAll(playersToRemove);
				playersToRemove.clear();
				
			}
			
		}
		
	}
	
	public void sendToAllBut(PlayerServerThread player, String info) {
		
		for(PlayerServerThread pl : players) {
			
			if (pl != player) {
				
				pl.writeInfo();
				
			}
			
		}
		
	}

	public int numPlayers() {
		return players.size();
	}
	
}
