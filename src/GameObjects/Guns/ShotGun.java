package GameObjects.Guns;

import Collision.PVector;
import GameObjects.Bullets.NormalBullet;
import Graphic.Canvas;
import Utils.PVectorUtil;

public class ShotGun extends Gun{

	private static final long serialVersionUID = -5801814393491090219L;

	public ShotGun(PVector p, Canvas canvas) {
		super(p, canvas);
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
			
			new NormalBullet(p, angleDirection + i * shiftAngle);
			
		}
		
	}
	
}
