package Utils;

public class Random {

	// Return double
	public static double randomDouble(int left, int right) {
		
		return Math.random() * (right - left) + left;
		
	}
	
	public static double randomDouble(int right) {
		
		return randomDouble(0, right);
		
	}
	
	// Returns int
	public static int random(int left, int right) {
		
		return (int) randomDouble(left, right);
		
	}
	
	public static int random(int right) {
		
		return (int) randomDouble(right);
		
	}
	
}
