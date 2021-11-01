package Server.Server.GameObjects.Bullets;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.StringTokenizer;

import Collision.CollisionEngine;
import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;
import Server.HTTPMessage;
import Server.Server.GameMaster;
import Server.Server.GameObjects.ServerGameObject;
import ServerData.BulletData;
import Utils.HTTPMessages;
import Utils.PVectorUtil;

import static Logger.Logger.*;

public class Bullet extends ServerGameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7971045488072260238L;

	public static Dimension bulletDimension = new Dimension(30, 8);
	
	protected PVector bulletPos;
	public int bulletSpeed = 4;
	protected double angleDirection;
	protected String bulletType;
	
	
	public Bullet(PVector pos, double angleDirection, ObjectOutputStream client) {
		
		super(client);
		
		this.bulletPos = pos;
		this.angleDirection = angleDirection;
		
		hitBox = new HitBox(bulletDimension, bulletPos, angleDirection);
		
	}
	
	public Bullet(BulletData bulletData, ObjectOutputStream client) {
		
		super(client);
		
		applyBulletData(bulletData);
		
		hitBox = new HitBox(bulletDimension, bulletPos, angleDirection);
		
	}

	public void applyBulletData(BulletData bulletData) {
		
		isDead = bulletData.isDead();		
		bulletPos = PVectorUtil.PVectorFromString(bulletData.getPos());
		angleDirection = bulletData.getAngleDirection();
		bulletType = bulletData.getBulletType();
		
	}
	
	public PVector getPos() {
		return this.bulletPos;
	}
	
	public double getAngleDirection() {
		return this.angleDirection;
	}
	
	public String getType() {
		return this.bulletType;
	}
	
	@Override
	public void update() {

		bulletPos.x += bulletSpeed * Math.cos(angleDirection);
		bulletPos.y += bulletSpeed * Math.sin(angleDirection);

		// If the bullet goes off-screen delete it
		if (bulletPos.x < - 100 || 
			bulletPos.x > GameMaster.config.width + 100 ||
			bulletPos.y < -100 ||
			bulletPos.y > GameMaster.config.width) {
			
			isDead = true;
			
			try {
				out.writeObject(getMessageForClient());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CollisionEngine.removeGameObject(this);
			
		}
		
		hitBox.update();
		
	}
	
	public HTTPMessage<?> getMessageForClient() {
		
		return new HTTPMessage<>(HTTPMessages.DRAW_BULLET, new BulletData(this));
		
	}
	
}
