package Graphic;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JPanel;

import Collision.CollisionEngine;
import GameObjects.GameObject;
import GameObjects.Player.EnemyPlayer;
import GameObjects.Player.MainPlayer;
import Server.HTTPEvent;
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
		
		canvas = new Canvas();
		canvas.addKeyListener(this);
		
		player = new MainPlayer(canvas);
		enemyPlayer = new EnemyPlayer(canvas);
		
//		for (int i = 0; i < 5; ++i)
//			new Enemy();

		mainThread = new Thread(this);
		mainThread.start();
		
		try {
			
			server = new Socket("localhost", 7777);
			new ServerListener(server, this);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageReceived(String message) {

		String [] data = message.split(";");
		
		int x = (int)Double.parseDouble(data[0]);
		int y = (int)Double.parseDouble(data[1]);
		
		enemyPlayer.setPos(x, y);
		
	}
	
}
