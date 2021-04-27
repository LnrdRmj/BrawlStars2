package Graphic;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import Utils.ImageUtils;

public class Sprite {

	private BufferedImage sprite;
	private int width;
	private int height;
	private float ratio;
	
	public Sprite(String pathToImage) {
		
		sprite = ImageUtils.getImage(pathToImage);
		
		width = sprite.getWidth();
		height = sprite.getHeight();
		ratio = width / height;
		
	}

	// Disegna sempre a (0, 0)
	public void draw(Graphics g) {
		
		g.drawImage(sprite, 0, 0, width, height, null);
		
	}
	
	public void flip() {
		
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
	    tx.translate(0, -sprite.getHeight(null));
	    AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    sprite = op.filter(sprite, null);
		
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setWidthMaintainRatio(int width) {
		this.width = width;
		this.height = (int)(width / ratio);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setHeightMaintainRatio(int height) {
		this.height = height;
		this.width = (int)(height * ratio);
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
}
