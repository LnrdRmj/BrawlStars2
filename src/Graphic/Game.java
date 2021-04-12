package Graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Collision.CollisionEngine;
import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Player;
import Utils.Renderer;
import Utils.Toast;

public class Game extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread mainThread;

	private Player gamer;

	Game() {

		super();

		this.add(Toast.toast);
		this.setBackground(Color.decode("#202020"));

		mainThread = new Thread(this);
		mainThread.start();

		// Con questo il keylistener funziona
		this.setFocusable(true);
		
	}
	
	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Renderer.render(g);

	}

	@Override
	public void run() {

		while (true) {

			//repaint();
			
			CollisionEngine.calculateCollision();
			
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

	public void setPlayer(Player player) {
		
		this.gamer = player;
		this.addKeyListener(gamer);
		this.addMouseListener(gamer.getGun());
		
	}
	
}
