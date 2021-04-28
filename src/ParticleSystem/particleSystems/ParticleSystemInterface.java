package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Vector;

import ParticleSystem.Particles.Particle;

public abstract class ParticleSystemInterface {

	protected Vector <Particle> particles;
	protected int numberParticles;
	
	public abstract void draw(Graphics g);
	public abstract boolean isDead();
	
}
