package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import Collision.PVector;
import ParticleSystem.Particles.Particle;
import ParticleSystem.Particles.TrailParticle;

public class ParticleSystemTrail extends ParticleSystemInterface{

	private boolean isDead = false;
	
	private PVector pos;
	private double angleDirection;
	private Vector<Particle> toAdd;
	
	public ParticleSystemTrail (PVector pos, double angleDirection) {
		
		particles = new Vector<>(2);
		
		this.pos = pos;
		this.angleDirection = angleDirection;
		toAdd = new Vector<>(2);
		
		// Le due particelle devono essere un l'opposta dell'altra
		particles.add(new TrailParticle(pos.x, pos.y, angleDirection + Math.PI / 2));
		particles.add(new TrailParticle(pos.x, pos.y, angleDirection - Math.PI / 2));
		
		Timer timer = new Timer();
		TrailTask shootTask = new TrailTask();
		
		timer.scheduleAtFixedRate(shootTask, 0, 300);
		
	}
	
	@Override
	public void draw(Graphics g) {

		for (int i = 0; i < particles.size(); ++i) {
			
			Particle p = particles.get(i);
			
			p.draw(g);
			p.update();
			
			if (p.isDead()) {
				
				particles.remove(p);
				
			}
			
		}
		
		if (toAdd.size() > 0) {
			particles.addAll(toAdd);
			toAdd.clear();
		}
		
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	class TrailTask extends TimerTask{

		@Override
		public void run() {
			
			toAdd.add(new TrailParticle(pos.x, pos.y, angleDirection + Math.PI / 2));
			toAdd.add(new TrailParticle(pos.x, pos.y, angleDirection - Math.PI / 2));
			
		}
		
	}
}
