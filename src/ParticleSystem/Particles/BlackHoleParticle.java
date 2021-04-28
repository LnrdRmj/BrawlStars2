package ParticleSystem.Particles;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.PVectorUtil;
import Utils.Random;

public class BlackHoleParticle extends Particle {

	private float x, y;

	private Color color;
	private float r;
	private double angle;
	private float distance;
	private float superForce;
	private int fadeOut;
	
	private int ttl;
	
	public BlackHoleParticle(float x, float y) {
		
		this.x = x;
		this.y = y;
		
		int colorValue = Random.random(40, 80);
		color = new Color(colorValue, 21, 130, 0);
		angle = Math.toRadians(Random.random(361));
		distance = Random.random(80, 120);
		superForce = 55;
		r = distance / 20 + 4;
		fadeOut = Random.random(11);
		ttl = Random.random(100);
		
		pos = new PVector(x + Math.cos(angle) * distance, y + Math.sin(angle) * distance);
		velocity = new PVector();
		acc = new PVector(x - pos.x, y - pos.y);
		acc.x /= 5000;
		acc.y /= 5000;
		
	}
	
	@Override
	public void update() {
		
		if (ttl > 0)
			--ttl;
		else if(ttl == 0) {
			--ttl;
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255);
		}
		else {
			velocity.add(acc);
			pos.add(velocity);
	//		distance = PVectorUtil.distance(pos.x, pos.y, this.x, this.y);
			distance -= Math.sqrt((velocity.x) * (velocity.x) + (velocity.y) * (velocity.y));
			
			if (distance <= superForce){
				acc.x *= 50;
				acc.y *= 50;
				superForce = 0;
			}
			
			r = distance / 20 + 4;
		}
		
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(color);
		g.fillOval((int)pos.x, (int)pos.y, (int)r, (int)r);
		
//		g.setColor(Color.white);
//		g.fillOval((int)this.x, (int)this.y, 2, 2);
		
	}

	@Override
	public boolean isDead() {
		return distance <= 0;
	}

	@Override
	public void reset() {

	}

}
