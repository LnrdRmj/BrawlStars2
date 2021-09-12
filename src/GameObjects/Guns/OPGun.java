package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.Bullet;
import Graphic.Canvas;

public class OPGun extends Gun{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8068617494904800050L;

	public OPGun(PVector p, Canvas canvas) {
		
		super(p, canvas);
		
		fireRate = 1000;
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		int n = 32;
		
		for (int i = 0; i < n; i++) {
			
			new Bullet((int)playerPos.x, (int)playerPos.y, angleDirection + 2 * Math.PI / n * i);
			
		}
		
	}
	
}
