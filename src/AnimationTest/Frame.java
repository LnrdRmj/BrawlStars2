package AnimationTest;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	public static void main (String []args) {
		
		JFrame frame = new Frame();
		
	}
	
	public Frame() {
		
		super();
		
		panel = new Panel();
		
		this.setBounds(new Rectangle(400, 400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(panel);
		
		this.setVisible(true);
		
	}
	
}
