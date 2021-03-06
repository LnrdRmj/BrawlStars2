package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Vector;

import ParticleSystem.Particles.Particle;
import ParticleSystem.Particles.ParticleExplosion;

public class ParticleSystemExplosion extends ParticleSystemInterface {

	private int nDead;
	
	private float x;
	private float y;
	
	public ParticleSystemExplosion (float x, float y) {
		
		super();
		
		numberParticles = 100;
		particles = new Vector<Particle>(numberParticles);
		
		for(int i = 0; i < numberParticles; ++i) particles.add(new ParticleExplosion(x, y));
		
		nDead = 0;
		
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public void draw(Graphics g) {

		for (int i = 0; i < particles.size(); ++i) {
			
			Particle p = particles.get(i);
			
			p.draw(g);
			p.update();
			
			if (p.isDead()) {
				
				isDead = true;
				nDead++;

			}
			
			if (nDead == numberParticles) {
				reset();
				nDead = 0;
			}
			
		}
		
	}

	public void reset() {
		
		for(int i = 0; i < numberParticles; ++i) particles.set(i, new ParticleExplosion(x, y));
		
	}
	
}
