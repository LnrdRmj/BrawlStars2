package GameObjects.Bullets;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import Collision.PVector;
import GameObjects.Guns.Gun;

public class BulletWithTrail extends Bullet{

	Timer timer;
	ShootTask shootTask;
	
	public BulletWithTrail(PVector pos, double angleDirection) {
		
		this(pos.x, pos.y, angleDirection);
		
	}
	
	public BulletWithTrail (float originX, float originY, double angleDirection) {
		
		this((int)originX, (int)originY, angleDirection);
		
	}
	
	public BulletWithTrail(int originX, int originY, int mouseX, int mouseY) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)));

	}

	public BulletWithTrail(int originX, int originY, double angleDirection) {
		
		super(originX, originY, angleDirection);

	}
	
	public void draw(Graphics g) {
		
		super.draw(g);
		
		
		
	}
	
	class ShootTask extends TimerTask{

		private MouseEvent e;

		@Override
		public void run() {
			createTrail();
		}
		
		public void setMouseEvent(MouseEvent e) {
			this.e = e;
		}
		
	}
	
}