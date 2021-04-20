package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;

public class Gun extends GameObject implements MouseListener{
	
	public static Dimension gunDimension = new Dimension(100, 5);
	
	private int fireRate = 100;
	
	Timer timer;
	ShootTask shootTask;

	protected PVector playerPos;
	protected Double angleDirection;
	
	Point mousePos;
	
	public Gun(PVector p) {
		super();
		
		setName("Pistola");
		playerPos = p;
		angleDirection = 0d;
		
		setHitBox(new HitBox(gunDimension, p, angleDirection));
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		new Bullet((int)playerPos.x, (int)playerPos.y, angleDirection);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch (e.getButton()) {
		// Left-click
		case 1:
			timer = new Timer();
			shootTask = new ShootTask(this);
			shootTask.setMouseEvent(e);
			timer.scheduleAtFixedRate(shootTask, 0, fireRate);
			break;

		default:
			break;
		}
		
		//shoot(e.getX(), e.getY());
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		switch (e.getButton()) {
		// Left-click
		case 1:
			timer.cancel();
			break;

		default:
			break;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void draw(Graphics g) {
		
		hitBox.draw(g);
		
	}
	
	public void update() {
		
		SwingUtilities.convertPointFromScreen(mousePos = MouseInfo.getPointerInfo().getLocation(), Frame.game.getCanvas());
		
		angleDirection = (Math.atan2((mousePos.y - playerPos.y), (mousePos.x - playerPos.x)));
		hitBox.setAngle(angleDirection);
		
	}

	@Override
	public void hit(GameObject hit) {
		
		
		
	}
	
}

class ShootTask extends TimerTask{

	private Gun gun;
	private MouseEvent e;
	
	public ShootTask (Gun gun) {
		this.gun = gun;
	}

	@Override
	public void run() {
		gun.shoot(e.getX(), e.getY());
	}
	
	public void setMouseEvent(MouseEvent e) {
		this.e = e;
	}
	
}