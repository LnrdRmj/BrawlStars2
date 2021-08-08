package Graphic;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public final static int gameWidth  = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
//	public final static int gameHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public final static int gameWidth  = 800;
	public final static int gameHeight = 400;
	
	public static Game game = new Game();

	public Frame() {
		
		super();
		
		this.setBounds(new Rectangle(gameWidth, gameHeight));
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().add(game.getCanvas());
		
		this.setVisible(true);
	}
	
}
