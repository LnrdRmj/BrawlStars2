package Server.Server;

import java.util.Vector;

import Server.HTTPMessage;
import Utils.HTTPMessages;

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
			
			players.forEach(player -> {
				
				player.update();
				
				// Quando il socket del player viene chiuso o disconnesso per qualche motivo
				if (player.isClosed()) {
					
					System.out.println("Un player si è disconnesso: id = " + player.getCode());
					
					playersToRemove.add(player);
					return;
					
				}
				
				sendInfoToAllBut(player);
				
			});

			if (playersToAdd.size() > 0) {
				
				players.addAll(playersToAdd);
				playersToAdd.clear();
				
			}
			
			if (playersToRemove.size() > 0) {
				
				players.removeAll(playersToRemove);
				playersToRemove.clear();
				
			}
			
			wait(16);
			
		}
		
	}
	
	private void wait(int milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void sendInfoToAllBut(PlayerServerThread player) {
		
//		System.out.println("Scrivo le informazioni di " + player.getCode() + " a:");
		
		for(PlayerServerThread pl : players) {
			
			if (pl != player) {
				
//				System.out.println("- " + pl.getCode());
				pl.write(new HTTPMessage<String>(HTTPMessages.PLAYER_POS, player.getInfo()));
//				pl.write(new HTTPMessage<>(HTTPMessages.TO_DRAW, player.getToUpdate()));
				
			}
			
		}
		
	}

	public int numPlayers() {
		return players.size();
	}
	
}
