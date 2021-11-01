package Server.Server.GameObjects.Bullets;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Collision.PVector;
import Server.HTTPMessage;
import ServerData.BulletData;
import Utils.HTTPMessages;
import Utils.PVectorUtil;

public class TrippleBullet extends Bullet {

	private static final long serialVersionUID = 2957182747963635174L;

	private List<NormalBullet> bullets;
	
	private final double spreadAangle = Math.toRadians(30);
	
	public TrippleBullet(PVector pos, double angleDirection, ObjectOutputStream client) {
		
		super(pos, angleDirection, client);
		
		bullets = new ArrayList<>();
		
		bullets.add(new NormalBullet(bulletPos.clone(), angleDirection + spreadAangle, client));
		bullets.add(new NormalBullet(bulletPos.clone(), angleDirection, client));
		bullets.add(new NormalBullet(bulletPos.clone(), angleDirection - spreadAangle, client));
		
	}

	public TrippleBullet(BulletData bulletData, ObjectOutputStream client) {
		
		super(client);
		
		bullets = new ArrayList<>();
		
		bullets.add(new NormalBullet(bulletPos.clone(), angleDirection + spreadAangle, client));
		bullets.add(new NormalBullet(bulletPos.clone(), angleDirection, client));
		bullets.add(new NormalBullet(bulletPos.clone(), angleDirection - spreadAangle, client));
		
	}

	@Override
	public void update() {
		
		Bullet bullet = null;
		
		for (Iterator<NormalBullet> itr = bullets.iterator(); itr.hasNext();) {
			
			bullet = itr.next();
			
			if (bullet.isDead()) itr.remove();
			
			bullet.update();
			
		}
		
		if (bullets.size() == 0) isDead = true;
		
	}
	
}
