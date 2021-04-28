package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.Bullet;

public class OPGun extends Gun{
	
	public OPGun(PVector p) {
		
		super(p);
		
		fireRate = 1000;
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		int n = 32;
		
		for (int i = 0; i < n; i++) {
			
			new Bullet((int)playerPos.x, (int)playerPos.y, angleDirection + 2 * Math.PI / n * i);
			
		}
		
	}
	
}
