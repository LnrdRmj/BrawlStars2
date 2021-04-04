package Utils;

public class PVectorUtil {

	public static PVector vectorBetween(PVector p1, PVector p2) {
		
		return new PVector(p1.x - p2.x, p1.y - p2.y);
		
	}
	
	public static PVector perpendicularVector(PVector p1) {
		
		float tmp = p1.x;
		p1.x = -p1.y;
		p1.y = tmp;
		
		return p1;
		
	}

	public static float dotProduct(PVector p1, PVector p2) {
		return p1.x * p2.x + p1.y * p2.y;
	}
	
}
