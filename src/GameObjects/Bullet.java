package GameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Collision.HitBox;
import Graphic.Game;
import Utils.PVector;
import Utils.Toast;

public class Bullet extends GameObject {

	public static Dimension bulletDimension = new Dimension(20, 10);
	public int bulletSpeed = 10;

	public static int bulletWidth;
	public static int bulletHeight;

	private double angleDirection;

	private PVector bulletPos;

	public Bullet(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public Bullet(int originX, int originY, double angleDirection) {
		super();
		
		setFillColor(Color.decode("#E26D5C"));
		
		//originPos = new PVector(originX, originY); 
		bulletPos = new PVector(originX, originY);
		
		//setShape(new Rectangle(Gun.gunDimension.width, 0, Bullet.bulletWidth, Bullet.bulletHeight));
		setShape(new HitBox(bulletDimension, bulletPos, angleDirection));
		
		Toast.setText("Angle = " + Math.toDegrees(angleDirection));
		this.angleDirection = angleDirection;
		
	}

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(fillColor);
		hitBox.draw(g2d);
		
		update();

	}

	public PVector rotatePoint(int x, int y, double angle) {
		
		float nx = (float) (x * Math.cos(angle) - y * Math.sin(angle));
		float ny = (float) (y * Math.cos(angle) + x * Math.sin(angle));
		
		PVector p = new PVector(nx, ny);
		
		return p;
	}
	
	public void update() {
		
		bulletPos.x += bulletSpeed * Math.cos(angleDirection);
		bulletPos.y += bulletSpeed * Math.sin(angleDirection);
		//distance += bulletSpeed;
		//setBounds((int) hitBox.getX() + bulletSpeed, (int) hitBox.getY(), bulletDimension.width, bulletDimension.height);
		
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
