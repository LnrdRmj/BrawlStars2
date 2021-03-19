package Graphic;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{
	
	public static JPanel game = new Game();
	
	public Frame() {
		
		super();
		
		this.setBounds(new Rectangle(1200, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(game);

		this.setVisible(true);
		
	}
	
}
