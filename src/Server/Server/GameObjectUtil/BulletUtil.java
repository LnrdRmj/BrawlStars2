package Server.Server.GameObjectUtil;

import java.io.ObjectOutputStream;

import GameObjects.GameObject;
import GameObjects.Bullets.BulletType;
import Graphic.Game;
import Server.Server.GameObjects.ServerGameObject;
import Server.Server.GameObjects.Bullets.Bullet;
import Server.Server.GameObjects.Bullets.TrippleBullet;
import ServerData.BulletData;

public class BulletUtil {

	public static ServerGameObject getBullet(BulletData bulletData, ObjectOutputStream out) {

		switch(bulletData.getBulletType()) {
		
		case BulletType.NORMAL_BULLET:
			return new Bullet(bulletData, out);
			
		case BulletType.TRIPPLE_BULLET:
			return new TrippleBullet(bulletData, out);	

		case BulletType.STANGE_BULLET:
			return new TrippleBullet(bulletData, out);	
	
		default:
			return new Bullet(bulletData, out);
			
		}
		
	}
	
	public static GameObject getBullet(BulletData bulletData) {

		switch(bulletData.getBulletType()) {
		
		case BulletType.NORMAL_BULLET:
			return new GameObjects.Bullets.Bullet(bulletData);
			
		case BulletType.TRIPPLE_BULLET:
			return new GameObjects.Bullets.TrippleBullets(bulletData);	
			
		case BulletType.STANGE_BULLET:
	
		default:
			return new GameObjects.Bullets.Bullet(bulletData);
			
		}
		
	}

}
