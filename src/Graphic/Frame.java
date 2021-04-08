package Graphic;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GameObjects.Player;

public class Frame extends JFrame{
	
	public static JPanel game = new Game();
	
	public final static int gameWidth  = 1200;
	public final static int gameHeight = 800;
	
	public Frame() {
		
		super();
		
		this.setBounds(new Rectangle(gameWidth, gameHeight));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		((Game) game).setPlayer(new Player());
		this.add(game);

		this.setVisible(true);
		
	}
	
}
