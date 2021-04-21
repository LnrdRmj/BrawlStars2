package Utils;

import Collision.PVector;

public class Force {

	private PVector goalForce;
	private PVector force;
	private PVector acceleration;
	
	public Force() {
		goalForce = new PVector(0, 0);
		force = new PVector(0, 0);
		acceleration = new PVector(0, 0);
	}
	
	public Force(PVector goalForce) {
		this.goalForce = goalForce;
		acceleration = new PVector(0, 0);
		this.force = new PVector(0, 0);
	}
	
	public Force(PVector goalForce, PVector acceleration) {
		this.goalForce = goalForce;
		this.acceleration = acceleration;
		this.force = new PVector(0, 0);
	}
	
	public Force(PVector goalForce, PVector acceleration, PVector force) {
		this.goalForce = goalForce;
		this.acceleration = acceleration;
		this.force = force;
	}
	
	public PVector applyForce() {
		
		this.force.add(this.acceleration);
		
//		System.out.println(goalForce.y - acceleration.y  + " <= " + this.force.y + " >= " + (goalForce.y + acceleration.y));
		
		if (this.force.x >= goalForce.x - Math.abs(acceleration.x) && this.force.x <= goalForce.x + Math.abs(acceleration.x)) 
			this.force.x = goalForce.x;
		
		if (this.force.y >= goalForce.y - Math.abs(acceleration.y) && this.force.y <= goalForce.y + Math.abs(acceleration.y))
			this.force.y = goalForce.y;
		
		return this.force;
		
	}
	
	public PVector getVector() {
		return force;
	}

	@Override
	public String toString() {
		return "Force [goalForce=" + goalForce + ", force=" + force + ", acceleration=" + acceleration + "]";
	}
	
	
	
}
