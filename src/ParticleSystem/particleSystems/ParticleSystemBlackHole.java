package ParticleSystem.particleSystems;

import java.awt.Graphics;
import java.util.Vector;

import ParticleSystem.Particles.BlackHoleParticle;
import ParticleSystem.Particles.Particle;

public class ParticleSystemBlackHole extends ParticleSystemInterface {

	private int nDead;
	
	private float x;
	private float y;
	
	public ParticleSystemBlackHole(float x, float y) {
	
		super();

		numberParticles = 100;
		particles = new Vector<Particle>(numberParticles);
		
		for(int i = 0; i < numberParticles; ++i) particles.add(new BlackHoleParticle(x, y));
		
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
				
				particles.remove(p);
				nDead++;

			}
			
			if (nDead == numberParticles) {
				isDead = true;
//				reset();
//				nDead = 0;
			}
			
		}
		
	}

	public void reset() {
		
		for(int i = 0; i < numberParticles; ++i) particles.add(new BlackHoleParticle(x, y));
		
	}

}
