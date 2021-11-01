package Server.Server.GameObjects.Bullets;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.StringTokenizer;

import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;
import ServerData.BulletData;

import static Logger.Logger.*;

public class NormalBullet extends Bullet implements Serializable{
	
	public NormalBullet(PVector pos, double angleDirection, ObjectOutputStream client) {
		
		super(client);
		
		this.bulletPos = pos;
		this.angleDirection = angleDirection;
		
		hitBox = new HitBox(bulletDimension, bulletPos, angleDirection);
		
	}
	
	public NormalBullet(BulletData bulletData, ObjectOutputStream client) {
		
		super(client);
		
		applyBulletData(bulletData);
		
		hitBox = new HitBox(bulletDimension, bulletPos, angleDirection);
		
	}
	
}
