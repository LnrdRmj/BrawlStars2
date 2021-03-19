package Graphic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import GameObjects.Player;
import Utils.Renderer;
import Utils.Toast;

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
		
		Renderer.render(g);
		
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
	
}
