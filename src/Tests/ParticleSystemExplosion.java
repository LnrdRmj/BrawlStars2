package Tests;

import java.awt.Graphics;
import java.util.Vector;

public class ParticleSystemExplosion extends ParticleSystemInterface {

	private boolean isDead = false;
	
	public ParticleSystemExplosion (float x, float y) {
		
		super();
		
		numberParticles = 50;
		particles = new Vector<Particle>(numberParticles);
		
		for(int i = 0; i < numberParticles; ++i) {
			
			particles.add(new ParticleExplosion(x, y));

		}
		
	}
	
	@Override
	public void draw(Graphics g) {

		for (int i = 0; i < particles.size(); ++i) {
			
			Particle p = particles.get(i);
			
			p.draw(g);
			p.update();
			
			if (p.isDead()) {

				isDead = true;

			}
			
		}
		
	}

	public boolean isDead() {
		
		return isDead;
		
	}
	
}
