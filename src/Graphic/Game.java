package Graphic;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JPanel;

import Collision.CollisionEngine;
import Collision.PVector;
import GameObjects.GameObject;
import GameObjects.ServerData;
import GameObjects.Bullets.Bullet;
import GameObjects.Player.EnemyPlayer;
import GameObjects.Player.MainPlayer;
import Server.HTTPEvent;
import Server.HTTPMessage;
import Server.RetryConnection;
import Server.Client.ServerListener;
import ServerData.BulletData;
import Utils.HTTPMessages;

import static Logger.Logger.*;

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
			
//			double start = System.currentTimeMillis();

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

	}
	
	public static void removeGameObject(GameObject toRemove) {
		
		Renderer.removeGameObjectToRender(toRemove);
		
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

	// Chiamato da ServerListener
	@Override
	public void onMessageReceived(HTTPMessage<?> message) {

//		System.out.println("Client - ho ricevuto qualcosa dal server: " + message);

		switch(message.getComand()) {
		
		case HTTPMessages.PLAYER_POS: 
			
			if (!(message.getMessageBody() instanceof String)) break;
			
			if (enemyPlayer == null) {
				
//				System.out.println("Primo messaggio: ho creato il nemico");
				enemyPlayer = new EnemyPlayer();
				
			}
			
			String [] data = ((String)message.getMessageBody()).split(";");
			
			if (data[0].equals("null") || data[1].equals("null")) return;
			
			int x = (int)Double.parseDouble(data[0]);
			int y = (int)Double.parseDouble(data[1]);
			
			enemyPlayer.setPos(x, y);
			
			break;
			
		case HTTPMessages.DRAW_BULLET:
			
			if (!(message.getMessageBody() instanceof BulletData)) break;
			
			BulletData d = (BulletData) message.getMessageBody();
//			logClient(d.getPos().toString());
//			logClient(d.getA().toString());
			
			StringTokenizer st = new StringTokenizer(d.getA(), ";");
			
			Renderer.addGameObjectToRender( new Bullet(new PVector(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())), d.getAngleDirection()) );
			
			break;
		
		}
		
	}

}
