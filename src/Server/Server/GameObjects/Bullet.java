package Server.Server.GameObjects;

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
	
	public Bullet(PVector pos, double angleDirection, ObjectOutputStream client) {
		
		this(pos.x, pos.y, angleDirection, client);
		
	}
	
	public Bullet (float originX, float originY, double angleDirection, ObjectOutputStream client) {
		
		this((int)originX, (int)originY, angleDirection, client);
		
	}
	
	public Bullet(int originX, int originY, int mouseX, int mouseY, ObjectOutputStream client) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)), client);

	}

	public Bullet(int originX, int originY, double angleDirection, ObjectOutputStream client) {
		
		super(client);
		
		
		bulletPos = new PVector(originX, originY);
		this.angleDirection = angleDirection;

		hitBox = new HitBox(bulletDimension, bulletPos, angleDirection);
		
	}
	
	public Bullet(BulletData bulletData, ObjectOutputStream client) {
		
		super(client);
		
		bulletPos = PVectorUtil.PVectorFromString(bulletData.getPos());
		this.angleDirection = bulletData.getAngleDirection();
		
		hitBox = new HitBox(bulletDimension, bulletPos, angleDirection);
		
	}

	public PVector getPos() {
		return this.bulletPos;
	}
	
	public double angleDirection() {
		return this.angleDirection;
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
			
			CollisionEngine.removeGameObject(this);
			
		}
		
		hitBox.update();
		
	}
	
	public HTTPMessage<?> getMessageForClient() {
		
		return new HTTPMessage<>(HTTPMessages.DRAW_BULLET, new BulletData(this));
		
	}
	
}
