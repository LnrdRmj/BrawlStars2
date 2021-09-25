package GameObjects.Bullets;

import Collision.PVector;
import GameObjects.Enemy;
import GameObjects.GameObject;
import Graphic.Game;
import ParticleSystem.ParticleSystemRenderer;
import ParticleSystem.particleSystems.ParticleSystemExplosion;

public class PierceBullet extends Bullet {

	public PierceBullet(PVector pos, double angleDirection) {
		
		this(pos.x, pos.y, angleDirection);
		
	}
	
	public PierceBullet (float originX, float originY, double angleDirection) {
		
		this((int)originX, (int)originY, angleDirection);
		
	}
	
	public PierceBullet(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public PierceBullet(int originX, int originY, double angleDirection) {
		
		super(originX, originY, angleDirection);

	}
	
	@Override
	public void hit(GameObject hit) {
		
		if (hit instanceof Enemy) {
			
			ParticleSystemRenderer.addParticleSystem(new ParticleSystemExplosion(bulletPos.x, bulletPos.y));
			
		}
		
	} 
	
}
