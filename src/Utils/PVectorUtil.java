package Utils;

import java.util.StringTokenizer;

import Collision.PVector;

public class PVectorUtil {

	public static PVector addVectors(PVector p1, PVector p2) {
		
		return addVectors(p1.x, p1.y, p2.x, p2.y);
		
	}
	
	public static PVector addVectors(double x1, double y1, double x2, double y2) {
		
		return new PVector(x1 + x2, y1 + y2);
		
	}
	
	public static PVector vectorBetween(PVector p1, PVector p2) {
		
		return new PVector(p1.x - p2.x, p1.y - p2.y);
		
	}
	
	public static PVector perpendicularVector(PVector p1) {
		
		PVector p2 = new PVector(p1.x, p1.y);
		
		double tmp = p2.x;
		p2.x = -p2.y;
		p2.y = tmp;
		
		return p2;
		
	}

	public static double dotProduct(PVector p1, PVector p2) {
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
	
	public static PVector rotatePoint(double cx, double cy, double x2, double y2, double angle) {
		
		float s = (float) Math.sin(angle);
		float c = (float) Math.cos(angle);

		// translate point back to origin:
		x2 -= cx;
		y2 -= cy;

		// rotate point
		double xnew = x2 * c - y2 * s;
		double ynew = x2 * s + y2 * c;

		// translate point back:
		x2 = xnew + cx;
		y2 = ynew + cy;

		return new PVector(x2, y2);
		
	}
	
	public static float distance(float x1, float y1, float x2, float y2) {
		
		return (float)Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		
	}
	
	public static PVector PVectorFromString(String pvector) {
		
		StringTokenizer str = new StringTokenizer(pvector, ";");
		
		return new PVector(Float.parseFloat(str.nextToken()), Float.parseFloat(str.nextToken()));
		
	}
	
}
