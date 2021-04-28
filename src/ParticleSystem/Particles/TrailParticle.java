package ParticleSystem.Particles;

import java.awt.Color;
import java.awt.Graphics;

import Collision.PVector;
import Utils.NumberUtils;
import Utils.PVectorUtil;

public class TrailParticle extends Particle {

	protected double angleDirection;
	private int r = 10;
	private int ttl = 60;
	private Color color;
	private int colorValue;
	
	public TrailParticle (float x, float y, double angleDirection) {
		
		this.pos = new PVector(x, y);
		this.velocity = PVectorUtil.rotatePoint(5, 0, angleDirection);
		this.angleDirection = angleDirection;
		
		colorValue = 141;
		color = new Color(50, colorValue, 168);
		
	}
	
	@Override
	public void update() {
		
		pos.add(velocity);
		
		colorValue = NumberUtils.mapInt(ttl, 0, 60, 50, 160);
		color = new Color(color.getRed(), colorValue, color.getBlue());
		
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
