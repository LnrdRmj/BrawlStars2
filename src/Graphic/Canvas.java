package Graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Collision.CollisionEngine;
import Utils.Renderer;
import Utils.Toast;

public class Canvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Canvas() {
		
		super();
		
		// Con questo il keylistener funziona
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		this.add(Toast.toast);
		this.setBackground(Color.decode("#202020"));
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Renderer.render(g);
		CollisionEngine.calculateCollision();

	}
	
}
