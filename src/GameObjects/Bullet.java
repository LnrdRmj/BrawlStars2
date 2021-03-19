package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import Utils.PVector;
import Utils.Toast;

public class Bullet extends GameObject {

	public static Dimension bulletDimension = new Dimension(20, 10);
	public int bulletSpeed = 5;

	public static int bulletWidth;
	public static int bulletHeight;

	private double angleDirection;
	private double distance;

	private PVector bulletPos;
	private PVector originPos;

	public Bullet(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public Bullet(int originX, int originY, double angleDirection) {
		super();
		
		originPos = new PVector(originX, originY); 
		bulletPos = new PVector(originX, originY);
		
		setShape(new Rectangle(Gun.gunDimension.width, 0, Bullet.bulletWidth, Bullet.bulletHeight));
		
		Toast.setText("Angle = " + Math.toDegrees(angleDirection));
		this.angleDirection = angleDirection;
		this.distance = 0;
		
	}

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		double nx = 0;
		double ny = 0;
		PVector p;
		
		int []pointX = new int [4];
		int []pointY = new int [4];
		// Draw-bullet branch
		
		bulletPos.x += bulletSpeed * Math.cos(angleDirection);
		bulletPos.y += bulletSpeed * Math.sin(angleDirection);
		
		g2d.drawOval((int)bulletPos.x, (int)bulletPos.y, 10, 10);
		
		// 0, 0
		pointX[0] = (int)(bulletPos.x);
		pointY[0] = (int)(bulletPos.y);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		//20, 0
		p = rotatePoint(bulletDimension.width, 0, angleDirection);
		nx = bulletPos.x + p.x;
		ny = bulletPos.y + p.y;
		pointX[1] = (int)(nx);
		pointY[1] = (int)(ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		// 20, 10
		p = rotatePoint(bulletDimension.width, bulletDimension.height, angleDirection);
		nx = bulletPos.x + p.x;
		ny = bulletPos.y + p.y;
		pointX[2] = (int)(nx);
		pointY[2] = (int)(ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		// 0, 10
		p = rotatePoint(0, bulletDimension.height, angleDirection);
		nx = bulletPos.x + p.x;
		ny = bulletPos.y + p.y;
		pointX[3] = (int)(nx);
		pointY[3] = (int)(ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		g2d.fillPolygon(pointX, pointY, 4);
		
		update();

	}

	public PVector rotatePoint(int x, int y, double angle) {
		
		float nx = (float) (x * Math.cos(angle) - y * Math.sin(angle));
		float ny = (float) (y * Math.cos(angle) + x * Math.sin(angle));
		
		PVector p = new PVector(nx, ny);
		
		return p;
	}
	
	public void update() {
		
		distance += bulletSpeed;
		setBounds((int) hitBox.getX() + bulletSpeed, (int) hitBox.getY(), bulletDimension.width, bulletDimension.height);
		
	}

}
