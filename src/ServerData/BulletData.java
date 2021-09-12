package ServerData;

import java.io.Serializable;

import Collision.PVector;

public class BulletData implements BasicData, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1581026129262363726L;
	
	private String bulletPos;
	private double angleDirection;
	
	public BulletData(String bulletPos, double angleDirection) {
		super();
		this.bulletPos = bulletPos;
		this.angleDirection = angleDirection;
	}
	
	public void setBulletPos(String bulletPos) {
		this.bulletPos = bulletPos;
	}

	public void setAngleDirection(double angleDirection) {
		this.angleDirection = angleDirection;
	}

	public String getPos() {
		return bulletPos;
	}

	public double getAngleDirection() {
		return angleDirection;
	}
	
}
