package GameObjects.Bullets;

import Collision.PVector;
import Graphic.Renderer;
import ServerData.BulletData;
import Utils.ImageUtils;
import Utils.PVectorUtil;

public class NormalBullet extends Bullet {

	public NormalBullet(PVector pos, double angleDirection) {
		
		super("Bullet");
		
		this.type = BulletType.NORMAL_BULLET;
		
		sprite = ImageUtils.getImage("Sprites/weapons/bullets/bullet.png");
		this.bulletPos = pos;
		this.angleDirection = angleDirection;
		
	}
	
	public NormalBullet(BulletData bulletData) {
		
		this(PVectorUtil.PVectorFromString(bulletData.getPos()), bulletData.getAngleDirection());
		
		this.code = bulletData.getCode();
		this.type = BulletType.NORMAL_BULLET;
		
	}

}
