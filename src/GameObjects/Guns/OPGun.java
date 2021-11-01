package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.Bullet;
import Graphic.Canvas;
import Utils.PVectorUtil;

public class OPGun extends Gun{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8068617494904800050L;

	public OPGun(PVector p, Canvas canvas) {
		
		super(p, canvas);
		
		fireRate = 1000;
		
	}
	
	@Override
	public void shoot(int mouseX, int mouseY) {
		
		int n = 32;
		
		for (int i = 0; i < n; i++) {
			
			new Bullet( new PVector(playerPos.x, playerPos.y), angleDirection + 2 * Math.PI / n * i);
			
		}
	
		
	}
	
}
