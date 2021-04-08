package AnimationTest;

import java.awt.Color;
import java.awt.Graphics;

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
		
		animator = new Animator(new PVector(25, 25), this, "Sprites\\8.png");
		
	}
	
//	public void paintComponent(Graphics g) {
//
//		super.paintComponent(g);
//		
//		int x = (currentFrameIndex % columns) * cStep;
//		int y = direction * rStep;
//		int widht = cStep;
//		int height = rStep;
//		
//		System.out.println(currentFrameIndex);
//		System.out.println(x);
//		System.out.println(y);
//		
//		frame.setData(framesImage.getData(new Rectangle(x, y, widht, height)));
//		g.drawImage(framesImage.getSubimage(x, y, widht, height), 0, 0, this);
//		
//	}

}
