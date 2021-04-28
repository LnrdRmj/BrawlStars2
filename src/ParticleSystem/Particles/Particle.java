package ParticleSystem.Particles;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;

public abstract class Particle {

	protected PVector pos;
	protected PVector velocity;
	protected PVector acc;
	
	protected Color color;
	
	public abstract void update();

	public abstract void draw(Graphics g);
	
	public abstract boolean isDead();
	
	public abstract void reset();
	
}
