package Tests;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.Random;

public class ParticleExplosion extends Particle{

	private Color color;
	
	private float r = 10;
	private double angle;
	private int distance = 0;
	
	private int ttl = 20;
	
	public ParticleExplosion(float x, float y) {

		angle = Math.toRadians(Random.random(361));
		
		pos = new PVector(x, y);
		velocity = new PVector(Math.cos(angle) * Random.random(1, 19) / 10, Math.sin(angle) * Random.random(1, 19) / 10);
		acc = new PVector(velocity.x / 10, velocity.y / 10);
		
		int colorValue = Random.random(30, 256);
		
		color = new Color(255, colorValue, colorValue);
		
	}
	
	public void reset() {
		
		
		
	}
	
	public void update() {
		
		if (ttl > 0) {
			
			this.velocity.add(this.acc);
			this.pos.add(this.velocity);
			
		}
		
		ttl--;
		
	}

	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillOval((int)pos.x, (int)pos.y, (int)r, (int)r);
		
	}
	
	public boolean isDead() {
		
		return ttl <= 0;
		
	}
	
}
