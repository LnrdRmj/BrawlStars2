package Graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Collision.CollisionEngine;
import GameObjects.FPSCounter;
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
		
		// Serve per posizionare fps e toast per bene a sinistra
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(Toast.toast);
		this.add(FPSCounter.fpsCounter);
		
		this.setBackground(Color.decode("#202020"));
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Renderer.render(g);
		CollisionEngine.calculateCollision();

	}
	
}
