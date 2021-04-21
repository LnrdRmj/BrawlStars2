package Utils;

import Collision.PVector;

public class PVectorUtil {

	public static PVector vectorBetween(PVector p1, PVector p2) {
		
		return new PVector(p1.x - p2.x, p1.y - p2.y);
		
	}
	
	public static PVector perpendicularVector(PVector p1) {
		
		PVector p2 = new PVector(p1.x, p1.y);
		
		float tmp = p2.x;
		p2.x = -p2.y;
		p2.y = tmp;
		
		return p2;
		
	}

	public static float dotProduct(PVector p1, PVector p2) {
		return p1.x * p2.x + p1.y * p2.y;
	}
	
	public static PVector rotatePoint(int x, int y, double angle) {
		
		return rotatePoint((float) x, (float) y, angle);
	
	}
	
	public static PVector rotatePoint(float x, float y, double angle) {
		
		float nx = (float) (x * Math.cos(angle) - y * Math.sin(angle));
		float ny = (float) (y * Math.cos(angle) + x * Math.sin(angle));
		
		PVector p = new PVector(nx, ny);
		
		return p;
		
	}
	
	public static PVector rotatePoint(float cx, float cy, float x2, float y2, double angle) {
		
		float s = (float) Math.sin(angle);
		float c = (float) Math.cos(angle);

		// translate point back to origin:
		x2 -= cx;
		y2 -= cy;

		// rotate point
		float xnew = x2 * c - y2 * s;
		float ynew = x2 * s + y2 * c;

		// translate point back:
		x2 = xnew + cx;
		y2 = ynew + cy;

		return new PVector(x2, y2);
		
	}
	
}
