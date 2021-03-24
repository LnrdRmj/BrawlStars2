package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Utils.PVector;

public class HitBox {

	private Dimension dim;
	private PVector origin;
	private double angle;
	
	private int pointX[];
	private int pointY[];
	
	public HitBox(Dimension dim, PVector origin, double angle) {
		super();
		this.angle = angle;
		this.origin = origin;
		this.dim = dim;
		
		pointX = new int [4];
		pointY = new int [4];
	}
	
	public void setBounds(int x, int y, int width, int height) {
		
		origin.x = x;
		origin.y = y;
		dim.width = width;
		dim.height = height;
		
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		double nx = 0;
		double ny = 0;
		PVector p;
		
		//g2d.drawOval((int)origin.x, (int)origin.y, 10, 10);
		
		// 0, 0
		pointX[0] = (int)(origin.x);
		pointY[0] = (int)(origin.y);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		//20, 0
		p = rotatePoint(dim.width, 0, angle);
		nx = origin.x + p.x;
		ny = origin.y + p.y;
		pointX[1] = (int)(nx);
		pointY[1] = (int)(ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		// 20, 10
		p = rotatePoint(dim.width, dim.height, angle);
		nx = origin.x + p.x;
		ny = origin.y + p.y;
		pointX[2] = (int)(nx);
		pointY[2] = (int)(ny);
//		g2d.fillOval((int)(originPos.x + nx), (int)(originPos.y + ny), r, r);
		
		// 0, 10
		p = rotatePoint(0, dim.height, angle);
		nx = origin.x + p.x;
		ny = origin.y + p.y;
		pointX[3] = (int)(nx);
		pointY[3] = (int)(ny);
		
		g2d.fillPolygon(pointX, pointY, 4);
		
	}
	
	public PVector rotatePoint(int x, int y, double angle) {
		
		float nx = (float) (x * Math.cos(angle) - y * Math.sin(angle));
		float ny = (float) (y * Math.cos(angle) + x * Math.sin(angle));
		
		PVector p = new PVector(nx, ny);
		
		return p;
	}

	public void updateAngle(double angleDirection) {
		
		angle = angleDirection;
		
	}

	public boolean collide(HitBox hb) {
		
		return false;
		
	}
	
	public void hit(HitBox hb) {
		
		
		
	}
}
