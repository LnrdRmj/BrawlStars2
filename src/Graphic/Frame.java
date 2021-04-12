package Graphic;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Game game = new Game();
	
	public final static int gameWidth  = 1200;
	public final static int gameHeight = 800;
	
	public Frame() {
		
		super();
		
		this.setVisible(true);
		
		this.setBounds(new Rectangle(gameWidth, gameHeight));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.add(game.getCanvas());
		
	}
	
}
