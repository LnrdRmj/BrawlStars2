package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import Utils.PVector;
import Utils.PVectorUtil;

import Global.Global;

public class HitBox {

	private Dimension dim;
	private PVector origin;
	private double angle;
	
	private int pointX[];
	private int pointY[];
	
	public HitBox(Dimension dim, PVector origin) {
		this(dim, origin, 0);
	}
	
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
		
		int [] objX = hb.getPointsX();
		int [] objY = hb.getPointsY();
		
		//PVector [] perpendicularVector = new PVector[pointX.length + objX.length]; 
		
		Vector <PVector> perpendicularVector = new Vector <PVector>();
		Vector <PVector> edges1 = new Vector <PVector>();
		Vector <PVector> edges2 = new Vector <PVector>();
		
		for (int i = 0; i < pointX.length; ++i) {
			
			int currentVertex = i;
			int nextVertex = (i + 1) % pointX.length;
			
			// Serve per creare il vettore tra due vertici consecutivi cioè lo spigolo spigolo			
			PVector perpendicular = PVectorUtil.vectorBetween(new PVector(pointX[currentVertex], pointY[currentVertex]),new PVector(pointX[nextVertex], pointY[nextVertex]));
			edges1.add(perpendicular);
			
			// Con questa funzione calcoliamo il vettore perpendicolare allo spigolo
			perpendicular = PVectorUtil.perpendicularVector(perpendicular);
			
			perpendicularVector.add(perpendicular);
			
			//Global.g.drawLine((int)origin.x, (int) origin.y, (int)origin.x + (int) perpendicularVector[i].x * 10, (int)origin.y + (int) perpendicularVector[i].y * 10);
		}
		
		for(int i = 0; i < objX.length; ++i) {
			
			int currentVertex = i;
			int nextVertex = (i + 1) % pointX.length;
			
			// Serve per creare il vettore tra due vertici consecutivi o il coseddetto spigolo			
			PVector perpendicular = PVectorUtil.vectorBetween(new PVector(objX[currentVertex], objY[currentVertex]),new PVector(objX[nextVertex], objY[nextVertex]));
			edges2.add(perpendicular);
			
			// Con questa funzione calcoliamo il vettore perpendicolare allo spigolo
			perpendicular = PVectorUtil.perpendicularVector(perpendicular);
			
			perpendicularVector.add(perpendicular);
			
		}
		
		for(PVector perp : perpendicularVector) {
			
			float max1 = -Integer.MAX_VALUE, max2 = -Integer.MAX_VALUE;
			float min1 =  Integer.MAX_VALUE, min2 =  Integer.MAX_VALUE;
			
			for (PVector edge : edges1) {
				
				float res = PVectorUtil.dotProduct(perp, edge);
				
				// Per trovare il max min
				if 		(max1 < res) max1 = res;
				else if	(min1 > res) min1 = res;
				
			}
			
			for (PVector edge : edges2) {
				
				float res = PVectorUtil.dotProduct(perp, edge);
				
				// Per trovare il max min
				if 		(max2 < res) max2 = res;
				else if	(min2 > res) min2 = res;
				
			}
			
			// Se non c'è intersezione all'ora non c'è collisione tra gli oggetti
			if (!(min1 < max2 && min2 < max1)) return false;
			
		}
		
		return true;
		
	}
	
	public int[] getPointsX() {
		return this.pointX;
	}

	public int[] getPointsY() {
		return this.pointY;
	}
	
}
