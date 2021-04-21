package Animation;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Panel panel = new Panel();
	
	public static void main (String []args) {
		
		new Frame();
		
	}
	
	public Frame() {
		
		super();
		
		this.setVisible(true);
		this.add(panel);
		
		this.setBounds(new Rectangle(400, 400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
