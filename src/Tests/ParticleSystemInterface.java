package Tests;

import java.awt.Graphics;
import java.util.Vector;

public abstract class ParticleSystemInterface {

	protected Vector <Particle> particles;
	protected int numberParticles;
	
	public abstract void draw(Graphics g);
	public abstract boolean isDead();
	
}
