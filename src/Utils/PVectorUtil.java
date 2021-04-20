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
		
		float nx = (float) (x * Math.cos(angle) - y * Math.sin(angle));
		float ny = (float) (y * Math.cos(angle) + x * Math.sin(angle));
		
		PVector p = new PVector(nx, ny);
		
		return p;
	}
	
}
