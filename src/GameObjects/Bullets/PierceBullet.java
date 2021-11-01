package GameObjects.Bullets;

import Collision.PVector;
import GameObjects.Enemy;
import GameObjects.GameObject;
import Graphic.Game;
import ParticleSystem.ParticleSystemRenderer;
import ParticleSystem.particleSystems.ParticleSystemExplosion;

public class PierceBullet extends Bullet {

	public PierceBullet(PVector pos, double angleDirection) {
		
		super(pos, angleDirection);
		
	}
	
	@Override
	public void hit(GameObject hit) {
		
		if (hit instanceof Enemy) {
			
			ParticleSystemRenderer.addParticleSystem(new ParticleSystemExplosion(bulletPos.clone()));
			
		}
		
	} 
	
}
