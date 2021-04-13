package Utils;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Toast{

	public static JLabel toast;
	
	static {
		
		toast = new JLabel("Hey");
		toast.setFont(new Font("font1", Font.BOLD, 40));
		toast.setForeground(Color.orange);
		
	}
	
	public static void setText(String text) {
		toast.setText(text);
	}
	
	public static void setText(Long number) {
		toast.setText(number.toString());
	}
	
	public static void setText(Double number) {
		toast.setText(number.toString());
	}
	
}
