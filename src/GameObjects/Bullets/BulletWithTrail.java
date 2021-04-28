package GameObjects.Bullets;

import java.awt.Graphics;

import Collision.PVector;
import ParticleSystem.ParticleSystemRenderer;
import ParticleSystem.particleSystems.ParticleSystemTrail;

public class BulletWithTrail extends Bullet{

	private ParticleSystemTrail ps;
	
	public BulletWithTrail(PVector pos, double angleDirection) {
		
		this(pos.x, pos.y, angleDirection);
		
	}
	
	public BulletWithTrail (float originX, float originY, double angleDirection) {
		
		this((int)originX, (int)originY, angleDirection);
		
	}
	
	public BulletWithTrail(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public BulletWithTrail(int originX, int originY, double angleDirection) {
		
		super(originX, originY, angleDirection);
		
		 ps = new ParticleSystemTrail(bulletPos, angleDirection);
		 
	}
	
	public void draw(Graphics g) {
		
		super.draw(g);
		
		ParticleSystemRenderer.addParticleSystem(ps);
		
	}
	
	@Override
	public void delete() {
		
		super.delete();
		ps.setDead(true);
		
	}
	
}