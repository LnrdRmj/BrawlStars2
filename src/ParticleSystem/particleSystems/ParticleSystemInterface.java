package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Vector;

import ParticleSystem.Particles.Particle;

public abstract class ParticleSystemInterface {

	protected Vector <Particle> particles;
	protected int numberParticles;
	protected boolean isDead;
	
	public abstract void draw(Graphics g);
	
	public void setDead(boolean dead) {
		
		isDead = dead;
		
	}
	
	public boolean isDead() {
		
		return isDead;
		
	}
	
}
