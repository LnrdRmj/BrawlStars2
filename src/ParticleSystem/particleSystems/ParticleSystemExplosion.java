package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Vector;

import Collision.PVector;
import ParticleSystem.Particles.Particle;
import ParticleSystem.Particles.ParticleExplosion;

public class ParticleSystemExplosion extends ParticleSystemInterface {

	private int nDead;
	
	private PVector pos;
	
	public ParticleSystemExplosion (PVector pos) {
		
		super();
		
		numberParticles = 100;
		particles = new Vector<Particle>(numberParticles);
		
		for(int i = 0; i < numberParticles; ++i) particles.add(new ParticleExplosion(pos));
		
		nDead = 0;
		
		this.pos = pos;
		
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
		
		for(int i = 0; i < numberParticles; ++i) particles.set(i, new ParticleExplosion(pos));
		
	}
	
}
