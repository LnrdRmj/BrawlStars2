package GameObjects.Bullets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Collision.PVector;
import ServerData.BulletData;

public class TrippleBullets extends NormalBullet {

	public TrippleBullets(PVector pos, double angleDirection) {
		super(pos, angleDirection);
		this.type = BulletType.TRIPPLE_BULLET;
	}
	
	public TrippleBullets(BulletData bulletData) {
		
		super(bulletData);
		this.type = BulletType.TRIPPLE_BULLET;
		
	}

	@Override
	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
				
		//		g.setColor(fillColor);
		//		hitBox.draw(g2d);
		
		AffineTransform old = g2d.getTransform();
		
		g2d.translate(bulletPos.x, bulletPos.y);
		g2d.rotate(angleDirection);
		
		g2d.drawImage(sprite, 0, 0, null);
		
		g2d.setTransform(old);
		
		
	}

}
