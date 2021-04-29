package ParticleSystem.Particles;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.NumberUtils;
import Utils.PVectorUtil;

public class TrailParticle extends Particle {

	protected double angleDirection;
	private int r = 10;
	private final int STARTING_TTL = 60;
	private int ttl = STARTING_TTL;
	private Color color;
	private int colorValue;
	
	public TrailParticle (float x, float y, double angleDirection) {
		
		this.pos = new PVector(x, y);
		this.velocity = PVectorUtil.rotatePoint(5, 0, angleDirection);
		this.angleDirection = angleDirection;
		
		colorValue = 141;
		color = new Color(50, colorValue, 168, 255);
		
	}
	
	@Override
	public void update() {
		
		if (ttl >= 0) {
			pos.add(velocity);
			
			colorValue = NumberUtils.mapInt(ttl, 0, 60, 50, 160);
			color = new Color(color.getRed(), colorValue, color.getBlue(), NumberUtils.mapInt(ttl, 0, STARTING_TTL, 0, 255));
		}
		
	}
	
	@Override
	public void draw(Graphics g) {

		g.setColor(color);
		g.fillOval((int)pos.x, (int)pos.y, r, r);
		ttl--;
		
	}

	@Override
	public boolean isDead() {
		return ttl < 0;
	}

	@Override
	public void reset() {

	}

}
