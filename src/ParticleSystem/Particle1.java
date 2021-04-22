package ParticleSystem;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.Random;

public class Particle1 extends Particle{

	private Color color;
	
	private int ttl;

	private float r = 10;
	
	public Particle1() {
		
		pos = new PVector();
		
		int range = 1;
		
		double x = Random.randomDouble(-range, range);
		double y = Random.randomDouble(-range, range);
		
//		velocity = new PVector(Random.randomDouble(-1, 1), Random.randomDouble(-1, 1));
//		velocity = new PVector(x, y);
		velocity = new PVector();
		double angle = Math.toRadians(Random.randomDouble(361));
		acc = new PVector(Math.cos(angle) / 100, Math.sin(angle) / 100);
		
		int colorValue = Random.random(30, 256);
		
		color = new Color(colorValue, 0, colorValue);
		
//		ttl = Random.random(200, 500);
		ttl = 250;
		
	}
	
	public void update() {
		
		if (ttl < 250) {
			
			this.velocity.add(this.acc);
			this.pos.add(this.velocity);
			
		}
		
		--ttl;
		
	}

	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillOval((int)pos.x, (int)pos.y, (int)r, (int)r);
		
	}
	
	public boolean isDead() {
		
		return ttl <= -1000;
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
