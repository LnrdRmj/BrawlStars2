package Animation;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Collision.PVector;
import Utils.ImageUtils;

public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Animator animator;
	
	public Panel() {

		this.setBackground(Color.decode("#202020"));
		
		BufferedImage i = ImageUtils.getImage("Sprites\\bullet.png");
		
		this.getGraphics().drawImage(i, 0, 0, null);
		
		animator = new Animator(new PVector(25, 25), "Sprites\\17.png");
		
	}

}
