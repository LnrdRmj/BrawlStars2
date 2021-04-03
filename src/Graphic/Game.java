package Graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Player;
import Utils.CollisionEngine;
import Utils.Renderer;
import Utils.Toast;

import Global.Global;

public class Game extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread mainThread;
	
	private Player gamer;
	
	Game(){
		
		super();
		
		this.add(Toast.toast);
		this.setBackground(Color.decode("#202020"));
		
		gamer = new Player();
		new Enemy();
		
		mainThread = new Thread(this);
		mainThread.start();
		
		// Con questo il keylistener funziona
		this.setFocusable(true);
		this.addKeyListener(gamer);
		this.addMouseListener(gamer.getGun());
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Global.g = g;
		
		Renderer.render(g);
		CollisionEngine.calculateCollision();
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			repaint();
			
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
	
}
