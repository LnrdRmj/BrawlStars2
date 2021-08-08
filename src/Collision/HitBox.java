package Collision;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Vector;

import Utils.PVectorUtil;

public class HitBox implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 795784307355957233L;
	
	private Dimension dim;
	private PVector origin;
	private Double angle;
	
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
		
		update();
		
	}
	
	public void setPos(int x, int y) {
		
		origin.x = x;
		origin.y = y;
		
	}
	
	public void setBounds(int x, int y, int width, int height) {
		
		origin.x = x;
		origin.y = y;
		dim.width = width;
		dim.height = height;
		
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		update();
		
		g2d.fillPolygon(pointX, pointY, 4);
		
	}
	
	public void update() {
		
		double nx = 0;
		double ny = 0;
		PVector p;
		
		// 0, 0
		pointX[0] = (int)(origin.x);
		pointY[0] = (int)(origin.y);
		
		//20, 0
		p = PVectorUtil.rotatePoint(dim.width, 0, angle);
		nx = origin.x + p.x;
		ny = origin.y + p.y;
		pointX[1] = (int)(nx);
		pointY[1] = (int)(ny);
		
		// 20, 10
		p = PVectorUtil.rotatePoint(dim.width, dim.height, angle);
		nx = origin.x + p.x;
		ny = origin.y + p.y;
		pointX[2] = (int)(nx);
		pointY[2] = (int)(ny);

		// 0, 10
		p = PVectorUtil.rotatePoint(0, dim.height, angle);
		nx = origin.x + p.x;
		ny = origin.y + p.y;
		pointX[3] = (int)(nx);
		pointY[3] = (int)(ny);

	}
	
	public void setAngle(double angleDirection) {
		
		angle = angleDirection;
		
	}

	public boolean collide(HitBox hb) {
		
		int [] objX = hb.getPointsX();
		int [] objY = hb.getPointsY();
		
		Vector <PVector> perpendicularVector = new Vector <PVector>();
		Vector <PVector> edges1 = new Vector <PVector>();
		Vector <PVector> edges2 = new Vector <PVector>();
		
		for (int i = 0; i < pointX.length; ++i) {
			
			int currentVertex = i;
			int nextVertex = (i + 1) % pointX.length;
			
			PVector vertex1 = new PVector(pointX[currentVertex], pointY[currentVertex]);
			PVector vertex2 = new PVector(pointX[nextVertex], pointY[nextVertex]);
			
			// Serve per creare il vettore tra due vertici consecutivi cioè lo spigolo spigolo			
			PVector vectorBetween = PVectorUtil.vectorBetween(vertex1, vertex2);
			edges1.add(vectorBetween);
			
			// Con questa funzione calcoliamo il vettore perpendicolare allo spigolo
			PVector perpendicular = PVectorUtil.perpendicularVector(vectorBetween);
			perpendicularVector.add(perpendicular);
			
		}

		for(int i = 0; i < objX.length; ++i) {
			
			int currentVertex = i;
			int nextVertex = (i + 1) % objX.length;
			
			PVector vertex1 = new PVector(objX[currentVertex], objY[currentVertex]);
			PVector vertex2 = new PVector(objX[nextVertex], objY[nextVertex]);
			
			// Serve per creare il vettore tra due vertici consecutivi o il coseddetto spigolo			
			PVector vectorBetween = PVectorUtil.vectorBetween(vertex1, vertex2);
			edges2.add(vectorBetween);
			
			// Con questa funzione calcoliamo il vettore perpendicolare allo spigolo
			PVector perpendicular = PVectorUtil.perpendicularVector(vectorBetween);
			
			perpendicularVector.add(perpendicular);
			
		}
		
		PVector vertex = new PVector();
		
		for(PVector perp : perpendicularVector) {
			
			float max1 = -Integer.MAX_VALUE, max2 = -Integer.MAX_VALUE;
			float min1 =  Integer.MAX_VALUE, min2 =  Integer.MAX_VALUE;
			
			for (int i = 0; i < pointX.length; ++i) {
				vertex.x = pointX[i];
				vertex.y = pointY[i];

				float res = PVectorUtil.dotProduct(perp, vertex);

				// Per trovare il max min
				if 		(max1 < res) max1 = res;
				if		(min1 > res) min1 = res;
				
			}
			
			for (int i = 0; i < objX.length; ++i) {
				vertex.x = objX[i];
				vertex.y = objY[i];
				
				float res = PVectorUtil.dotProduct(perp, vertex);

				// Per trovare il max min
				if 		(max2 < res) max2 = res;
				if		(min2 > res) min2 = res;
				
			}
			
			// Se non c'è intersezione all'ora non c'è collisione tra gli oggetti
			if (!((min1 < max2 && min1 > min2) || (min2 < max1 && min2 > min1))) return false;
			
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
