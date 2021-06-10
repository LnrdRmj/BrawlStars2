package Graphic;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JPanel;

import org.omg.Messaging.SyncScopeHelper;

import Collision.CollisionEngine;
import GameObjects.GameObject;
import GameObjects.Player.EnemyPlayer;
import GameObjects.Player.MainPlayer;
import Server.HTTPEvent;
import Server.HTTPMessage;
import Server.RetryConnection;
import Server.Client.ServerListener;

public class Game implements Runnable, KeyListener, HTTPEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread mainThread;
	private MainPlayer player;
	private Canvas canvas;
	private Socket server;
	private EnemyPlayer enemyPlayer;

	Game() {
		startNewGame();
	}
	
	private void startNewGame() {
		
		try {
			
			server = new Socket("localhost", 7777);
			new ServerListener(server, this);
			
		} catch (IOException e) {
			
			// Prova ogni 5 secondi a riconnetterti al server
			new RetryConnection("localhost", 7777, (s) -> {
			
				server = s;
				new ServerListener(server, this);
				player.setSocket(server);
				
			}, 5000);
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}

		canvas = new Canvas();
		canvas.addKeyListener(this);
		
		player = new MainPlayer(canvas);
		if (server != null) player.setSocket(server);
//		enemyPlayer = new EnemyPlayer();
		
//		for (int i = 0; i < 5; ++i)
//			new Enemy();

		mainThread = new Thread(this);
		mainThread.start();
		
	}
	
	public void run() {

		while (true) {
			
			double start = System.currentTimeMillis();

			canvas.repaint();

			// 60 Frames BABYYYY
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

	public static void addGameObject(GameObject toAdd) {

		Renderer.addGameObjectToRender(toAdd);
		CollisionEngine.addGameObject(toAdd);

	}
	
	public static void removeGameObject(GameObject toRemove) {
		
		Renderer.removeGameObjectToRender(toRemove);
		CollisionEngine.removeGameObject(toRemove);
		
	}

	public JPanel getCanvas() {
		return canvas;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyChar()) {
		case '': // R: Non so perch� ma dovrebbe essere una R maiuscola
			
			if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) {
				
				System.out.println("New game");
//				startNewGame();
				
			}
			
			break;

		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void onMessageReceived(HTTPMessage<?> message) {

//		System.out.println("Client - ho ricevuto qualcosa dal server: " + message);

		switch(message.getComand()) {
		
		case "playerPos": 
			
			if (enemyPlayer == null) {
				
//				System.out.println("Primo messaggio: ho creato il nemico");
				enemyPlayer = new EnemyPlayer();
				
			}
			
			String [] data = ((String)message.getMessageBody()).split(";");
			
			if (data[0].equals("null")) return;
			
//			System.out.println("Info di " + data[2]);
			int x = (int)Double.parseDouble(data[0]);
			int y = (int)Double.parseDouble(data[1]);
			
			enemyPlayer.setPos(x, y);
			
			break;
		
		}
		
		
		
	}

}
