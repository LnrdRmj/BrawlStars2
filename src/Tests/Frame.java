package Tests;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int width = 1920;
	public static int height = 1080;
	
	public static void main (String []args) {

		new Frame();
		
	}
	
	public Frame() {
		
		super();

		this.setBounds(new Rectangle(width, height));
		
		this.add(new Panel());
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
