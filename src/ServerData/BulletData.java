package ServerData;

import java.io.Serializable;

import Collision.PVector;

public class BulletData implements BasicData, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1581026129262363726L;
	
	private PVector bulletPos;
	private double angleDirection;
	private String a;
	
	public BulletData(PVector bulletPos, double angleDirection, String a) {
		super();
		this.bulletPos = bulletPos;
		this.angleDirection = angleDirection;
		this.a = a;
	}
	
	public void setBulletPos(PVector bulletPos) {
		this.bulletPos = bulletPos;
	}

	public void setAngleDirection(double angleDirection) {
		this.angleDirection = angleDirection;
	}

	public void setA(String a) {
		this.a = a;
	}

	public PVector getPos() {
		return bulletPos;
	}

	public double getAngleDirection() {
		return angleDirection;
	}
	
	public String getA() {
		return a;
	}

}
