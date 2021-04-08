package Animation;

import java.awt.Color;

import javax.swing.JPanel;

import Utils.PVector;

public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Animator animator;
	
	public Panel() {

		this.setBackground(Color.decode("#202020"));
		
		animator = new Animator(new PVector(25, 25), this, "Sprites\\17.png");
		
	}

}
