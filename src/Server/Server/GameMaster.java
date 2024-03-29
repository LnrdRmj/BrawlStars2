package Server.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import javax.lang.model.element.Element;

import Server.Config;
import Server.HTTPMessage.HTTPMessage;
import Server.HTTPMessage.HTTPMessageFactory;
import Server.Server.GameObjects.ServerGameObject;
import ServerData.HandShakeDataServerToClient;
import ServerData.PlayerData;
import Utils.HTTPMessages;

public class GameMaster implements Runnable{

	private List<PlayerServerThread> players;
	private List<PlayerServerThread> playersToAdd;
	private List<PlayerServerThread> playersToRemove;
	
	private List<ServerGameObject> gameObjects;
	private List<ServerGameObject> gameObjectsToAdd;
	private List<ServerGameObject> gameObjectsToRemove;
	
	public static Config config;
	
	public GameMaster() {
		
		players				= new Vector<>();
		playersToAdd 		= new Vector<>();
		playersToRemove 	= new Vector<>();
		
		gameObjects 		= new Vector<>();
		gameObjectsToAdd 	= new Vector<>();
		gameObjectsToRemove = new Vector<>();
		
		config = new Config();

		new Thread(this).start();
		
	}
	
	public void addPlayerThread(PlayerServerThread newPlayer) {
		
		newPlayer.addOnNewGameObject(el -> {
			
			gameObjects.add(el);
			
		});

		newPlayer.addOnDeadGameObject(el -> {
			
			gameObjects.remove(el);
			
		});

		gameObjects.add(newPlayer);
		
		sendMessageToAll(HTTPMessageFactory.getNewEnemyMessage(newPlayer));
		
		this.playersToAdd.add(newPlayer);
		
	}

	@Override
	public void run() {

		while(true) {
		
			// Ciclo per aggiornare e mandare i gameobjects a tutti i client
			gameObjects.forEach( gameObject -> {
			
				// Se � morto lo tolgo
				if (gameObject.isDead())
					gameObjectsToRemove.add(gameObject);
				
				// Aggiorno i gameObjects
				gameObject.update();
			
				// Mando i gameObjects a tutti i players
				players.forEach( player -> {
					
					HTTPMessage<?> a = gameObject.getMessageForClient();
					player.write(a);
					
				});
			
			});	

			players.forEach( player -> {
				
				if (player.isClosed()) {
					
					playersToRemove.add(player);
					gameObjectsToRemove.add(player);
					player.kill();
					
					sendMessageToAllBut(player, HTTPMessageFactory.getRemoveEnemyMessage(player));
					
				}
					
			});
			
			// -----------------------------------------------------------------
			// Aggiunta o eliminazione elementi da players
			// -----------------------------------------------------------------
			if (playersToAdd.size() > 0) {
				
				players.addAll(playersToAdd);
				playersToAdd.clear();
				
			}
			
			if (playersToRemove.size() > 0) {
				
				players.removeAll(playersToRemove);
				playersToRemove.clear();
				
			}
			
			// -----------------------------------------------------------------
			// Aggiunta o eliminazione elementi da gameObjects
			// -----------------------------------------------------------------
			if (gameObjectsToAdd.size() > 0) {
				
				gameObjects.addAll(gameObjectsToAdd);
				gameObjectsToAdd.clear();
				
			}
			
			if (gameObjectsToRemove.size() > 0) {
				
				gameObjects.removeAll(gameObjectsToRemove);
				gameObjectsToRemove.clear();
				
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

	public void sendMessageToAllBut(PlayerServerThread player, HTTPMessage<?> message) {
		
		for(PlayerServerThread pl : players) {
			
			if (pl != player) {
				
				pl.write(message);
				
			}
			
		}
		
	}
	
	public void sendMessageToAll(HTTPMessage<?> message) {
		
		for(PlayerServerThread pl : players) {
			
			pl.write(message);
			
		}
		
	}

	public int numPlayers() {
		return players.size();
	}
	
}
