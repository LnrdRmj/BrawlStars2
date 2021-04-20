package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static BufferedImage getImage(String path) {
		
		BufferedImage framesImage = null;
		
		try {
			
			framesImage = ImageIO.read(new File(path));
			
		} catch (IOException ex) {
			
			System.out.println("Qualcosa è andato storto");
			
		}
		
		return framesImage;
		
	}
	
}
