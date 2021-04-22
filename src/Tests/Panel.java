package Tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParticleSystemInterface sys;
	
	public Panel() {
		
		this.setBackground(Color.decode("#202020"));
		
		sys = new ParticleSystemExplosion(TestParticleSystem.width / 2, TestParticleSystem.height / 2);
		
		new Thread(this).start();
		
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
		
		g.setColor(Color.WHITE);
		
		sys.draw(g);
		
	}
	
	@Override
	public void run() {

		while (true) {
			
			repaint();
			
			wait(16);
			
		}

	}

	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
