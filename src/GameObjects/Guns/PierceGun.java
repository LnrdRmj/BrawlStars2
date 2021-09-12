package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.BulletWithTrail;
import Graphic.Canvas;
import Utils.PVectorUtil;

public class PierceGun extends Gun {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006349511560210708L;

	public PierceGun(PVector p, Canvas canvas) {
		super(p, canvas);
	}

	@Override
	public void shoot(int mouseX, int mouseY) {
		
		PVector p = PVectorUtil.rotatePoint(playerPos.x + adjustedmentPosition.x, playerPos.y + adjustedmentPosition.y, playerPos.x + adjustedmentPosition.x + gunDimension.width, playerPos.y + adjustedmentPosition.y + gunDimension.height / 2, angleDirection);
		
		new BulletWithTrail(p.x, p.y, angleDirection);
		
	}
	
}
