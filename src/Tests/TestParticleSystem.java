package Tests;

import java.awt.Rectangle;

import javax.swing.JFrame;

import Utils.NumberUtils;

public class TestParticleSystem extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int width = 800;
	public static int height = 800;
	
	public static void main (String []args) {

		new TestParticleSystem();
		
	}
	
	public TestParticleSystem() {
		
		super();

		this.setBounds(new Rectangle(width, height));
		
		this.add(new Panel());
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
