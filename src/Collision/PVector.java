package Collision;

import java.io.Serializable;

public class PVector implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 554080776356314892L;
	
	public float x;
	public float y;
	
	public PVector(double x, double y) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	public PVector(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public PVector(){
		this(0, 0);
	}
	
	public PVector(PVector copy){
		this(copy.x, copy.y);
	}
	
	public void add(PVector toAdd) {
		
		this.x += toAdd.x;
		this.y += toAdd.y;
		
	}
	
	public void add(float x, float y) {
		
		this.x += x;
		this.y += y;
		
	}
	
	public void sub(PVector toAdd) {
		
		this.x -= toAdd.x;
		this.y -= toAdd.y;
		
	}
	
	public void sub(float x, float y) {
		
		this.x -= x;
		this.y -= y;
		
	}
	
	public void reset() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public String toString() {
		return "PVector [x=" + x + ", y=" + y + "]";
	}
	
}
