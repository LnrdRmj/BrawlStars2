package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import Collision.PVector;
import ParticleSystem.Particles.Particle;
import ParticleSystem.Particles.TrailParticle;

public class ParticleSystemTrail extends ParticleSystemInterface{

	private PVector pos;
	private double angleDirection;
	private Vector<Particle> toAdd;
	
	private boolean shouldDie;
	private int intervalTime;
	private Timer timer;
	private TrailTask shootTask;
	
	public ParticleSystemTrail (PVector pos, double angleDirection) {
		
		particles = new Vector<>(2);
		
		this.pos = pos;
		this.angleDirection = angleDirection;
		toAdd = new Vector<>(2);
		
		shouldDie = false;
		intervalTime = 10;
		
		timer = new Timer();
		shootTask = new TrailTask();		
		timer.scheduleAtFixedRate(shootTask, 0, intervalTime);
		
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
		
		if (shouldDie && particles.size() == 0) {
			isDead = true;
		}
		else if (shouldDie) {
			
			timer.cancel();
			timer.purge();
			
		}
		
	}

	@Override
	public void setDead(boolean shouldDie) {
		
		this.shouldDie = shouldDie;
		
	}
	
	@Override
	public boolean isDead() {
		
		return shouldDie && isDead;
		
	}

	class TrailTask extends TimerTask{

		@Override
		public void run() {
			
			// Le due particelle devono essere un l'opposta dell'altra
			toAdd.add(new TrailParticle(pos.x, pos.y, angleDirection + Math.PI / 2));
			toAdd.add(new TrailParticle(pos.x, pos.y, angleDirection - Math.PI / 2));
			
		}
		
	}
}
