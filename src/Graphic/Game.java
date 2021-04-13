package Graphic;


import javax.swing.JPanel;

import Collision.CollisionEngine;
import GameObjects.Enemy;
import GameObjects.FPSCounter;
import GameObjects.GameObject;
import GameObjects.Player;
import Utils.Renderer;

public class Game implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread mainThread;
	private Player player;
	private Canvas canvas;

	Game() {

		canvas = new Canvas();
		
		player = new Player(canvas);
		
		System.out.println(canvas.isFocusable());
		System.out.println(canvas.getKeyListeners().length);
		
		new Enemy();

		mainThread = new Thread(this);
		mainThread.start();
		
	}
	
	public void run() {

		while (true) {
			
			double start = System.currentTimeMillis();

			canvas.repaint();

			// 60 Frames BABYYYY
			wait(16);
			// some time passes
			
			double end = System.currentTimeMillis();
			double elapsedTime = end - start;

			System.out.println(elapsedTime);
//			FPSCounter.setText(1 / (elapsedTime / 1000));
			
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

	public void setPlayer(Player player) {
		
		this.player = player;
		canvas.addKeyListener(this.player);
		canvas.addMouseListener(this.player.getGun());
		
	}

	public JPanel getCanvas() {
		return canvas;
	}
	
}
