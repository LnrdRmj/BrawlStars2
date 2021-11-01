package Server.Server.GameObjectUtil;

import java.io.ObjectOutputStream;

import GameObjects.GameObject;
import GameObjects.Bullets.BulletType;
import Graphic.Game;
import Server.Server.GameObjects.ServerGameObject;
import Server.Server.GameObjects.Bullets.NormalBullet;
import Server.Server.GameObjects.Bullets.TrippleBullet;
import ServerData.BulletData;

public class BulletUtil {

	public static ServerGameObject getBullet(BulletData bulletData, ObjectOutputStream out) {

		switch(bulletData.getBulletType()) {
		
		case BulletType.NORMAL_BULLET:
			return new NormalBullet(bulletData, out);
			
		case BulletType.TRIPPLE_BULLET:
			return new TrippleBullet(bulletData, out);	

		case BulletType.STANGE_BULLET:
			return new TrippleBullet(bulletData, out);	
	
		default:
			return new NormalBullet(bulletData, out);
			
		}
		
	}
	
	public static GameObject getBullet(BulletData bulletData) {

		switch(bulletData.getBulletType()) {
		
		case BulletType.NORMAL_BULLET:
			return new GameObjects.Bullets.NormalBullet(bulletData);
			
		case BulletType.TRIPPLE_BULLET:
			return new GameObjects.Bullets.TrippleBullets(bulletData);	
			
		case BulletType.STANGE_BULLET:
	
		default:
			return new GameObjects.Bullets.NormalBullet(bulletData);
			
		}
		
	}

}
