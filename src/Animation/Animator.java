package Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Utils.PVector;

import Global.Global;

public class Animator {

	private final int AVANTI = 0;
	private final int SINISTRA = 1;
	private final int DESTRA = 2;
	private final int INDIETRO = 3;

	private int direction = AVANTI;

	private int fps = 8;
	private int frameInterval = 1000 / fps;

	private BufferedImage framesImage;

	private int currentFrameIndex;

	private int rows = 4;
	private int columns = 4;
	private int rStep;
	private int cStep;

	Timer t;
	TimerTask animation;

	private PVector pos;
	private JPanel panel;

	public Animator(PVector pos, JPanel panel, String framesImagePath) {

		this.pos = pos;
		this.panel = panel;

		framesImage = getImage(framesImagePath); // Sets framesImage
		System.out.println(framesImage.getWidth() + "X" + framesImage.getHeight());

		rStep = framesImage.getHeight() / rows;
		cStep = framesImage.getWidth() / columns;

		currentFrameIndex = 0;

		t = new Timer();

		animation = new TimerTask() {

			@Override
			public void run() {

				currentFrameIndex++;

			}

		};

		t.scheduleAtFixedRate(animation, frameInterval, frameInterval);

	}

	private void paint() {
		
		int x = (currentFrameIndex % columns) * cStep;
		int y = direction * rStep;
		int width = cStep;
		int height = rStep;
		
		try {
			panel.getGraphics().drawImage(framesImage.getSubimage(x, y, width, height), (int)pos.x, (int)pos.y, null);
		}
		catch (NullPointerException e) {
			
			System.out.println(e);
			
		}
			//		Global.g.drawImage(framesImage.getSubimage(x, y, width, height), (int)pos.x, (int)pos.y, null);

	}
	
	private BufferedImage getImage(String path) {
		
		BufferedImage framesImage = null;
		
		try {
		
			framesImage = ImageIO.read(new File(path));
			
		} catch (IOException ex) {
			
			System.out.println("Qualcosa � andato storto");
		
		}

		return framesImage;
		
	}

	public void drawFrame(Graphics g) {
		
		int x = (currentFrameIndex % columns) * cStep;
		int y = direction * rStep;
		int width = cStep;
		int height = rStep;
		
		try {
			g.drawImage(framesImage.getSubimage(x, y, width, height), (int)pos.x, (int)pos.y, null);
		}
		catch (NullPointerException e) {
			
			System.out.println(e);
			
		}
		
	}

}
