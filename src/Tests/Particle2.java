package Tests;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.Random;

public class Particle2 extends Particle{

	private Color color;
	
	private int ttl;

	private float r = 10;
	
	public Particle2() {
		
		pos = new PVector(Frame.width / 2, Frame.height / 2);
		
		int range = 1;
		
		double x = Random.randomDouble(-range, range);
		double y = Random.randomDouble(-range, range);
		
//		velocity = new PVector(Random.randomDouble(-1, 1), Random.randomDouble(-1, 1));
		velocity = new PVector(x, y);
		acc = new PVector(- x / 100, - y / 100);
		
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
	
}
