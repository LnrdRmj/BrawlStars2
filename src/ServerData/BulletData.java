package ServerData;

import java.io.Serializable;

import Server.Server.GameObjects.Bullet;

public class BulletData implements BasicData, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1581026129262363726L;
	
	private Integer code;
	private String bulletPos;
	private double angleDirection;
	
	public BulletData(String bulletPos, double angleDirection) {
		super();
		this.bulletPos = bulletPos;
		this.angleDirection = angleDirection;
	}
	
	public BulletData(String bulletPos, double angleDirection, Integer code) {
		super();
		this.bulletPos = bulletPos;
		this.angleDirection = angleDirection;
		this.code = code;
	}
	
	public BulletData(Bullet bullet) {
		
		this.bulletPos = bullet.getPos().x + ";" + bullet.getPos().y;
		this.angleDirection = bullet.angleDirection();
		this.code = bullet.getCode();
		
	}

	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "BulletData [code=" + code + ", bulletPos=" + bulletPos + ", angleDirection=" + angleDirection + "]";
	}
	
	
	
}
