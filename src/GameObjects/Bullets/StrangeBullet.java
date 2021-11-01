package GameObjects.Bullets;

import Collision.PVector;
import ServerData.BulletData;
import Utils.ImageUtils;
import Utils.PVectorUtil;

public class StrangeBullet extends Bullet {

	public StrangeBullet(PVector pos, double angleDirection) {
		
		super(pos, angleDirection);
		
		this.type = BulletType.STANGE_BULLET;
		sprite = ImageUtils.getImage("Sprites/weapons/bullets/small_bullet.png");
		
	}
	
	public StrangeBullet(BulletData bulletData) {
		
		this(PVectorUtil.PVectorFromString(bulletData.getPos()), bulletData.getAngleDirection());
		
		this.type = BulletType.STANGE_BULLET;
		
	}

	
	
}
