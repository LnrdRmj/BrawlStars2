package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

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
		bulletPos = new PVector();
		
		setShape(new Rectangle(Gun.gunDimension.width, 0, Bullet.bulletWidth, Bullet.bulletHeight));
		
		Toast.setText("Angle = " + Math.toDegrees(angleDirection));
		this.angleDirection = angleDirection;
		this.distance = 0;
		
	}

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		double nx = 0;
		double ny = 0;
		
		int []pointX = new int [4];
		int []pointY = new int [4];
		// Draw-bullet branch
		nx = distance * Math.cos(angleDirection);
		ny = distance * Math.sin(angleDirection);
		bulletPos.x = (float) nx;
		bulletPos.y = (float) ny;
		pointX[0] = (int)(originPos.x + nx);
		pointY[0] = (int)(originPos.y + ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		nx = (distance + bulletDimension.width) * Math.cos(angleDirection);
		ny = (distance + bulletDimension.width) * Math.sin(angleDirection);
		pointX[1] = (int)(originPos.x + nx);
		pointY[1] = (int)(originPos.y + ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		nx = bulletPos.x + 0 * Math.cos(angleDirection) - bulletDimension.height * Math.sin(angleDirection);
		ny = bulletPos.y + bulletDimension.height * Math.cos(angleDirection) + 0 * Math.sin(angleDirection);
		pointX[3] = (int)(originPos.x + nx);
		pointY[3] = (int)(originPos.y + ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		nx = bulletPos.x + bulletDimension.width * Math.cos(angleDirection) - bulletDimension.height * Math.sin(angleDirection);
		ny = bulletPos.y + bulletDimension.height * Math.cos(angleDirection) + bulletDimension.width * Math.sin(angleDirection);
		pointX[2] = (int)(originPos.x + nx);
		pointY[2] = (int)(originPos.y + ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		g2d.fillPolygon(pointX, pointY, 4);
		
		update();

	}

	public void update() {
		
		distance += bulletSpeed;
		setBounds((int) hitBox.getX() + bulletSpeed, (int) hitBox.getY(), bulletDimension.width, bulletDimension.height);
		
	}

}
