package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.BulletWithTrail;
import Utils.PVectorUtil;

public class PierceGun extends Gun {

	public PierceGun(PVector p) {
		super(p);
	}

	@Override
	public void shoot(int mouseX, int mouseY) {
		
		PVector p = PVectorUtil.rotatePoint(playerPos.x + adjustedmentPosition.x, playerPos.y + adjustedmentPosition.y, playerPos.x + adjustedmentPosition.x + gunDimension.width, playerPos.y + adjustedmentPosition.y + gunDimension.height / 2, angleDirection);
		
		new BulletWithTrail(p.x, p.y, angleDirection);
		
	}
	
}
