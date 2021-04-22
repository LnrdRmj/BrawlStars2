package ParticleSystem;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.Random;

public class ParticleExplosion extends Particle{

	private Color color;
	private float r;
	private double angle;
	private int distance;
	private int fadeOut;
	
	private int ttl;
	
	public ParticleExplosion(float x, float y) {

		int colorValue = Random.random(30, 256);
		color = new Color(204, colorValue, 18);
		r = 10;
		angle = Math.toRadians(Random.random(361));
		distance = 0;
		fadeOut = Random.random(11);
		ttl = 20;
		
		pos = new PVector(x, y);
		velocity = new PVector(Math.cos(angle) * Random.random(1, 19) / 10, Math.sin(angle) * Random.random(1, 19) / 10);
		acc = new PVector(velocity.x / 10, velocity.y / 10);
		
		
		
	}
	
	public void reset() {
		
		
		
	}
	
	public void update() {
		
		if (ttl > 0) {
			
			this.velocity.add(this.acc);
			this.pos.add(this.velocity);
			
		}
		
		r = ttl / 2 + 5;
		distance++;
		--ttl;
		
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255 - distance * fadeOut);
		
	}

	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillOval((int)pos.x, (int)pos.y, (int)r, (int)r);
		
	}
	
	public boolean isDead() {
		
		return ttl <= 0;
		
	}
	
}
