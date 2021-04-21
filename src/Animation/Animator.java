package Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import Collision.PVector;
import Utils.ImageUtils;

public class Animator{

	public static final int AVANTI = 0;
	public static final int SINISTRA = 1;
	public static final int DESTRA = 2;
	public static final int INDIETRO = 3;
	
	private final int DEFAULT_DIRECTION_FRAME = AVANTI;

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
	
	private boolean isRunning = false;

	public Animator(PVector pos, String framesImagePath) {

		this.pos = pos;

		framesImage = ImageUtils.getImage(framesImagePath); // Sets framesImage

		rStep = framesImage.getHeight() / rows;
		cStep = framesImage.getWidth() / columns;

		currentFrameIndex = 0;
		
		t = new Timer();

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

	public boolean isRunning() {
		return isRunning;
	}
	
	public void start() {
		
		start(direction);
		
	}
	
	public void start(int direction) {
		
		isRunning = true;
		
		this.direction = direction;
		
		t.cancel();
		t.purge();
		
		t = new Timer();
		t.scheduleAtFixedRate(getTask(), 0, frameInterval);
		
	}
	
	public void setDirection(int direction) {
		
		this.direction = direction;
		
	}
	
	public void stop() {

		isRunning = false;
		
		t.cancel();
		t.purge();
		
		currentFrameIndex = 0;
		
	}
	
	public void stopAndSetDefaultFrame() {

		stop();
		direction = DEFAULT_DIRECTION_FRAME;
		
	}

	public int getWidthFrame() {
		
		return cStep;
		
	}

	public int getHeightFrame() {
		
		return rStep;
		
	}
	
	private TimerTask getTask() {
		
		return new TimerTask() {

			@Override
			public void run() {
				
				// Questo serve per far progredire l'animazione
				currentFrameIndex++;

			}

		};
		
	}
	
	public static int WASDtoDirection(String wasd) {
		
		switch (wasd) {
			case "w":	return INDIETRO;
			case "a":	return SINISTRA;
			case "s":	return AVANTI;
			case "d":	return DESTRA;
		}
		
		return -1;
		
	}
	
}
