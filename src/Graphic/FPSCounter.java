package Graphic;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class FPSCounter {

	public static JLabel fpsCounter;
	
	static {
		
		fpsCounter = new JLabel("FPS!");
		fpsCounter.setFont(new Font("font1", Font.BOLD, 40));
		fpsCounter.setForeground(Color.orange);
		
	}
	
	public static void setText(String text) {
		fpsCounter.setText(text);
	}
	
	public static void setText(Long number) {
		fpsCounter.setText(number.toString());
	}
	
	public static void setText(Double number) {
		fpsCounter.setText(number.toString());
	}
	
}
