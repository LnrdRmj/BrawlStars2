package Graphic;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GameObjects.Enemy;
import GameObjects.Player;

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
		
		Game.addGameObject(new Player());
		Game.addGameObject(new Enemy());
		this.add(game);

	}
	
}
