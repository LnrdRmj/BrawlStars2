package Server.Server.GameObjects;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Collision.CollisionEngine;
import Collision.HitBox;
import Collision.PVector;
import GameObjects.ServerData;
import Graphic.Frame;
import Server.HTTPMessage;
import Server.Server.GameMaster;
import ServerData.BulletData;
import Utils.HTTPMessages;

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
	
	private BulletData bulletData;
		
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
		
//		setName("Proiettile");
		
		bulletPos = new PVector(originX, originY);

		serverData = new ServerData(new HitBox(bulletDimension, bulletPos, angleDirection));
		
		this.angleDirection = angleDirection;
		
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
		
		serverData.getHitBox().update();
		
	}
	
	public HTTPMessage<?> getMessageForClient() {
		
		return new HTTPMessage<>(HTTPMessages.DRAW_BULLET, new BulletData(bulletPos.x + ";" + bulletPos.y, angleDirection));
		
	}
	
}
