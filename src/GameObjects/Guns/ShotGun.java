package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.Bullet;
import Utils.PVectorUtil;

public class ShotGun extends Gun{

	public ShotGun(PVector p) {
		super(p);
	}

	@Override
	public void shoot(int mouseX, int mouseY) {
		
		double shiftAngle = Math.toRadians(10);
		
		PVector p;
		
		for (int i = -3; i < 3; ++i) {
		
			p = PVectorUtil.rotatePoint(
					playerPos.x + adjustedmentPosition.x,
					playerPos.y + adjustedmentPosition.y, 
					playerPos.x + adjustedmentPosition.x + gunDimension.width, playerPos.y + adjustedmentPosition.y + gunDimension.height / 2, 
					angleDirection);
			
			new Bullet(p.x, p.y, angleDirection + i * shiftAngle);
		
		}
		
	}
	
}
