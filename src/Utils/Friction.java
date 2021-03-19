package Utils;

public class Friction{
	
	public static PVector calculateFriction(PVector force) {
		
		return new PVector(force.x * -0.03f, force.y * -0.03f);
		
	}
	
}
