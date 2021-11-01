package ServerData;

import java.io.Serializable;

import Server.Server.GameObjects.Bullets.Bullet;

public class BulletData implements BasicData, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1581026129262363726L;
	
	private Integer code;
	private String bulletType;
	private String bulletPos;
	private double angleDirection;
	private boolean isDead;
	
	public BulletData(String bulletPos, double angleDirection, String bulletType) {
		super();
		this.bulletPos = bulletPos;
		this.angleDirection = angleDirection;
		this.bulletType = bulletType;
	}
	
	public BulletData(String bulletPos, double angleDirection, Integer code, String bulletType) {
		super();
		this.bulletPos = bulletPos;
		this.angleDirection = angleDirection;
		this.code = code;
		this.bulletType = bulletType;
	}
	
	public BulletData(Bullet bullet) {
		
		this.bulletPos = bullet.getPos().x + ";" + bullet.getPos().y;
		this.angleDirection = bullet.getAngleDirection();
		this.code = bullet.getCode();
		this.isDead = bullet.isDead();
		this.bulletType = bullet.getType();
		
	}
	
	public BulletData(GameObjects.Bullets.Bullet bullet) {
		
		this.bulletPos = bullet.getBulletPos().x + ";" + bullet.getBulletPos().y;
		this.angleDirection = bullet.getAngleDirection();
		this.code = bullet.getCode();
		this.bulletType = bullet.getType();
		
	}

	public String getBulletType() {
		return bulletType;
	}

	public void setBulletType(String bulletType) {
		this.bulletType = bulletType;
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
		return "BulletData [code=" + code + ", bulletType=" + bulletType + ", bulletPos=" + bulletPos
				+ ", angleDirection=" + angleDirection + ", isDead=" + isDead + "]";
	}

	public boolean isDead() {
		return isDead;
	}
	
	
	
}
