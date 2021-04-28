package Utils;

public class NumberUtils {

	public static float map(int n, int l1, int r1, int l2, int r2) {
		
		float intervallo1 = r1 - l1;
		float intervallo2 = r2 - l2;
		
		float ratio = intervallo2 / intervallo1;
		
		return l2 + (n - l1) * ratio;
		
	}
	
	public static int mapInt(int n, int l1, int r1, int l2, int r2) {
		
		return (int)map(n, l1, r1, l2, r2);
		
	}
	
}
