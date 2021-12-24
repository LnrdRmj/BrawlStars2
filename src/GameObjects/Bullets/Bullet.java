package GameObjects.Bullets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Collision.HitBox;
import Collision.PVector;
import GameObjects.Enemy;
import GameObjects.GameObject;
import Graphic.Game;
import ParticleSystem.ParticleSystemRenderer;
import ParticleSystem.particleSystems.ParticleSystemExplosion;
import ServerData.BasicData;
import ServerData.BulletData;
import Utils.PVectorUtil;

public abstract class Bullet extends GameObject {

	protected String type;
	protected BufferedImage sprite;
	public static Dimension bulletDimension = new Dimension(30, 8);
	public int bulletSpeed = 30;
	protected double angleDirection;
	protected PVector bulletPos;

	public Bullet(PVector pos, double angleDirection) {
		
		this.bulletPos = pos;
		this.angleDirection = angleDirection;
		
	}
	
	public Bullet(String name) {
		super(name);
	}

	public Bullet() {
		super();
	}

	public Bullet(HitBox hitBox) {
		super(hitBox);
	}

	public void draw(Graphics g) {
			
			Graphics2D g2d = (Graphics2D) g;
			
	//		g.setColor(fillColor);
	//		hitBox.draw(g2d);
			
			AffineTransform old = g2d.getTransform();
			
			g2d.translate(bulletPos.x, bulletPos.y);
			g2d.rotate(angleDirection);
			
			g2d.drawImage(sprite, 0, 0, null);
			
			g2d.setTransform(old);
			
		}

	public void update() {}

	public void hit(GameObject hit) {}

	public PVector getBulletPos() {
		return bulletPos;
	}

	public double getAngleDirection() {
		return angleDirection;
	}

	public String getType() {
		
		return this.type;
		
	}

	public void applyData(BasicData data) {
	
		if (data instanceof BulletData) {
			
			BulletData bulletData = (BulletData) data;
			
			this.bulletPos = PVectorUtil.pVectorFromString(bulletData.getPos());
			this.angleDirection = bulletData.getAngleDirection();
			
		}
		
	}

}