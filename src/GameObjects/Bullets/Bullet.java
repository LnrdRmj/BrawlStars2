package GameObjects.Bullets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Collision.PVector;
import GameObjects.Enemy;
import GameObjects.GameObject;
import Graphic.Game;
import Graphic.Renderer;
import ParticleSystem.ParticleSystemRenderer;
import ParticleSystem.particleSystems.ParticleSystemExplosion;
import ServerData.BasicData;
import ServerData.BulletData;
import Utils.ImageUtils;
import Utils.PVectorUtil;

public class Bullet extends GameObject {

	private BufferedImage sprite;
	
	public static Dimension bulletDimension = new Dimension(30, 8);
	public int bulletSpeed = 30;

	protected double angleDirection;

	protected PVector bulletPos;

	public Bullet(PVector pos, double angleDirection) {
		
		this(pos.x, pos.y, angleDirection);
		
	}
	
	public Bullet (float originX, float originY, double angleDirection) {
		
		this((int)originX, (int)originY, angleDirection);
		
	}
	
	public Bullet(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public Bullet(int originX, int originY, double angleDirection) {
		
		super("Bullet");
		
		sprite = ImageUtils.getImage("Sprites/weapons/bullets/bullet.png");
		
		bulletPos = new PVector(originX, originY);
		
		this.angleDirection = angleDirection;
		
		Renderer.addGameObjectToRender(this);
		
	}

	public Bullet(BulletData bulletData) {
		
		this(PVectorUtil.PVectorFromString(bulletData.getPos()), bulletData.getAngleDirection());
		
		this.code = bulletData.getCode();
		
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
	
	public void update() {
		
		// Non so se vale per tutti ma nel caso dei proiettili vanno renderizzati
	    // solo una volta sola perché il frame successivo ce lo darà il server
//		Renderer.removeGameObjectToRender(this);
		
	}
	
	public void delete () {
		
		Game.removeGameObject(this);
		
	}

	@Override
	public void hit(GameObject hit) {
		
		if (hit instanceof Enemy) {
			
			ParticleSystemRenderer.addParticleSystem(new ParticleSystemExplosion(bulletPos.x, bulletPos.y));
			delete();
			
		}
		
	}

	
	
	public PVector getBulletPos() {
		return bulletPos;
	}

	public double getAngleDirection() {
		return angleDirection;
	}

	@Override
	public void applyData(BasicData data) {

		if (data instanceof BulletData) {
			
			BulletData bulletData = (BulletData) data;
			
			this.bulletPos = PVectorUtil.PVectorFromString(bulletData.getPos());
			this.angleDirection = bulletData.getAngleDirection();
			
		}
		
	}

}
