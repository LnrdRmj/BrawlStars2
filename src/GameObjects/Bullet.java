package GameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;
import Graphic.Game;
import Utils.ImageUtils;

public class Bullet extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1388429583603351070L;

	private BufferedImage sprite;
	
	public static Dimension bulletDimension = new Dimension(20, 10);
	public int bulletSpeed = 10;

	public static int bulletWidth;
	public static int bulletHeight;

	private double angleDirection;

	private PVector bulletPos;

	public Bullet(PVector pos, double angleDirection) {
		
		this(pos.x, pos.y, angleDirection);
		
	}
	
	public Bullet (float originX, float originY, double angleDirection) {
		
		this((int)originX, (int)originY, angleDirection);
		
	}
	
	public Bullet(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public Bullet(int originX, int originY, double angleDirection) {
		
		super();
		
		setFillColor(Color.decode("#E26D5C"));
		setName("Proiettile");
		
		sprite = ImageUtils.getImage("Sprites\\bullet.png");
		
		bulletPos = new PVector(originX, originY);
		
		setHitBox(new HitBox(bulletDimension, bulletPos, angleDirection));
		
		this.angleDirection = angleDirection;
		
	}

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
//		g.setColor(fillColor);
//		hitBox.draw(g2d);
		
		AffineTransform old = g2d.getTransform();
		
		g2d.translate(bulletPos.x, bulletPos.y);
		g2d.rotate(angleDirection);
		
		g2d.drawImage(sprite, 0, 0, null);
		
		g2d.setTransform(old);
		
	}
	
	public void update() {
		
		bulletPos.x += bulletSpeed * Math.cos(angleDirection);
		bulletPos.y += bulletSpeed * Math.sin(angleDirection);

		// If the bullet goes off-screen delete it
		if (bulletPos.x < - 100 || 
			bulletPos.x > Frame.gameWidth + 100 ||
			bulletPos.y < -100 ||
			bulletPos.y > Frame.gameHeight) {
			
			Game.removeGameObject(this);
			
		}
		
		hitBox.update();
		
	}

	@Override
	public void hit(GameObject hit) {
		
		switch (hit.getClass().toString().substring(7)) {
		case "Player":
			
			break;
			
		case "Enemy":
			
			break;

		default:
			break;
		}
		
	}

}
