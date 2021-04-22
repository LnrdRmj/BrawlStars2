package Graphic;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Collision.CollisionEngine;
import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Player;

public class Game implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread mainThread;
	private Player player;
	private Canvas canvas;

	Game() {
		startNewGame();
	}
	
	private void startNewGame() {
		
		canvas = new Canvas();
		canvas.addKeyListener(this);
		
		player = new Player(canvas);
		
		for (int i = 0; i < 10; ++i)
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
			
			double end = System.currentTimeMillis();
			double elapsedTime = end - start;

//			System.out.println(elapsedTime);
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
		case '': // R: Non so perché ma dovrebbe essere una R maiuscola
			
			
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
	
}
