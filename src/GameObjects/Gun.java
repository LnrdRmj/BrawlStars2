package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;
import Graphic.Sprite;
import Utils.PVectorUtil;

public class Gun extends GameObject implements MouseListener{
	
//	public static Dimension gunDimension = new Dimension(100, (int)(100 / 2.8));
	public static int gunWidth = 100;
	public static int gunHeight;
	public static Dimension gunDimension;
	
	protected int fireRate = 100;
	
	Timer timer;
	ShootTask shootTask;
	
	protected PVector playerPos;
	protected Double angleDirection;
	protected PVector adjustedmentPosition;
	
	Point mousePos;
	
	public Gun(PVector p) {
		
		super();
		
		setName("Pistola");
		sprite = new Sprite("Sprites/weapons/assaultrifle.png");
		sprite.setWidthMaintainRatio(gunWidth = 200);
		
		gunDimension = new Dimension(sprite.getWidth(), sprite.getHeight());
		
		playerPos = p;
		angleDirection = 0d;
		adjustedmentPosition = new PVector(10, 20);
		
		setHitBox(new HitBox(gunDimension, p, angleDirection));
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		PVector p = PVectorUtil.rotatePoint(playerPos.x, playerPos.y, playerPos.x + gunDimension.width, playerPos.y + gunDimension.height / 2, angleDirection);
		
		new Bullet(p.x, p.y, angleDirection);
		
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
		
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform old = g2d.getTransform();
		
		g2d.translate(playerPos.x, playerPos.y);
		g2d.rotate(angleDirection);
		
		sprite.draw(g2d);
		
		g2d.setTransform(old);
		
	}
	
	@Override
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