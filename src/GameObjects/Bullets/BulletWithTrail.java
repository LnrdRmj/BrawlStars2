package GameObjects.Bullets;

import java.awt.Graphics;

import Collision.PVector;
import ParticleSystem.ParticleSystemRenderer;
import ParticleSystem.particleSystems.ParticleSystemTrail;

public class BulletWithTrail extends Bullet{

	private ParticleSystemTrail ps;
	
	public BulletWithTrail(PVector pos, double angleDirection) {
		
		super(pos, angleDirection);
		
	}
	
	@Override
	public void kill() {
		
		super.kill();
		
		ps.setDead(true);
		
	}
	
}