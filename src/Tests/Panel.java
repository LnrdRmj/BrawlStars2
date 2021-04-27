package Tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ParticleSystem.ParticleSystemInterface;
import Utils.ImageUtils;

public class Panel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParticleSystemInterface sys;
	
	public Panel() {
		
		this.setBackground(Color.decode("#202020"));
		
//		sys = new ParticleSystemBlackHole(TestParticleSystem.width / 2, TestParticleSystem.width / 2);
//		
//		new Thread(this).start();
		
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
//		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); 
//		
//		g.setColor(Color.WHITE);
//		
//		sys.draw(g);
		
		BufferedImage bufferedImage = ImageUtils.getImage("Sprites/weapons/assaultrifle.png");
		
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
	    tx.translate(0, -bufferedImage.getHeight(null));
	    AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    bufferedImage = op.filter(bufferedImage, null);
		
		g.drawImage(bufferedImage, 0, 0, null);
		
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
