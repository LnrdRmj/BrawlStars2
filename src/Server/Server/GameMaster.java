package Server.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import javax.lang.model.element.Element;

import Server.HTTPMessage;
import Server.Server.GameObjects.ServerGameObject;
import Utils.HTTPMessages;

public class GameMaster implements Runnable{

	private List<PlayerServerThread> players;
	private List<PlayerServerThread> playersToAdd;
	private List<PlayerServerThread> playersToRemove;
	
	private List<ServerGameObject> gameObjects;
	
	public GameMaster() {
		
		players = new Vector<PlayerServerThread>();
		playersToAdd = new Vector<PlayerServerThread>();
		playersToRemove = new Vector<PlayerServerThread>();
		
		gameObjects = new Vector<>();
		
		new Thread(this).start();
		
	}
	
	public void addPlayerThread(PlayerServerThread player) {
		
		player.addOnNewGameObject(el -> {
			
			gameObjects.add(el);
			
		});

		player.addOnDeadGameObject(el -> {
			
			gameObjects.remove(el);
			
		});

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
					
					sendMessageToAllBut(player, new HTTPMessage<>(HTTPMessages.REMOVE_ENEMY, player.getCode() + ""));
					
					playersToRemove.add(player);
					return;
					
				}
				
				sendAllGameObjectsToPLayer(player);
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
	
	private void sendAllGameObjectsToPLayer(PlayerServerThread player) {

		ObjectOutputStream out = player.getSocketOut();
		
		gameObjects.forEach( el -> {
			
			try {
				out.writeObject(el.getMessageForClient());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
	}

	private void wait(int milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void sendMessageToAllBut(PlayerServerThread player, HTTPMessage<?> message) {
		
		for(PlayerServerThread pl : players) {
			
			if (pl != player) {
				
				pl.write(message);
				
			}
			
		}
		
	}
	
	public void sendInfoToAllBut(PlayerServerThread player) {
		
//		System.out.println("Scrivo le informazioni di " + player.getCode() + " a:");
		
		for(PlayerServerThread pl : players) {
			
			if (pl != player) {
				
				player.writeAllInfo(pl.getSocketOut());
				
			}
			
		}
		
	}

	public int numPlayers() {
		return players.size();
	}
	
}
