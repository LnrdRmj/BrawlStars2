package GameObjects.Guns;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.swing.SwingUtilities;

import Collision.HitBox;
import Collision.PVector;
import GameObjects.GameObject;
import GameObjects.ServerData;
import GameObjects.Bullets.Bullet;
import Graphic.Canvas;
import Graphic.Frame;
import Graphic.Renderer;
import Graphic.Sprite;
import Utils.Observer;
import Utils.PVectorUtil;

public class Gun extends ServerData implements MouseListener, GameObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//	public static Dimension gunDimension = new Dimension(100, (int)(100 / 2.8));
	public static int gunWidth;
	public static int gunHeight;
	public static Dimension gunDimension;
	
	protected int fireRate = 100;
	
	Timer timer;
	ShootTask shootTask;
	
	protected PVector playerPos;
	protected Double angleDirection;
	protected PVector adjustedmentPosition;
	
	protected ArrayList<Consumer<Bullet>> onGunShot;
	
	Point mousePos;
	
	protected Sprite sprite;
	
	private Canvas canvas;
	
	/*
	 * 0 - Da decidere ancora al primo loop
	 * 1 - vuol dire che la pistola sta a destra e quindi non � flippata
	 * 2 - lo sprite della pistola sta a sinistra e quindi � flippata
	 * */
	private int isFlipped;
	
	public Gun(PVector p, Canvas canvas) {
		
		super();
		
		setName("Pistola");
		sprite = new Sprite("Sprites/weapons/shotgun.png");
		sprite.setWidthMaintainRatio(50);
		
		gunDimension = new Dimension(sprite.getWidth(), sprite.getHeight());
		
		playerPos = p;
		angleDirection = 0d;
		adjustedmentPosition = new PVector(25, 40);
		
		setHitBox(new HitBox(gunDimension, p, angleDirection));
		
		isFlipped = 0;
		
		this.canvas = canvas;
		
		onGunShot = new ArrayList<>();
		
		Renderer.addGameObjectToRender(this);
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		PVector p = PVectorUtil.rotatePoint(playerPos.x + adjustedmentPosition.x, playerPos.y + adjustedmentPosition.y, playerPos.x + adjustedmentPosition.x + gunDimension.width, playerPos.y + adjustedmentPosition.y + gunDimension.height / 2, angleDirection);
		
		Bullet bulletShot = new Bullet(p.x, p.y, angleDirection);
		
		// Notifica tutti gli observer
		onGunShot.forEach( obs -> obs.accept(bulletShot) );
		
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
		
//		hitBox.draw(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform old = g2d.getTransform();
		
		g2d.translate(playerPos.x + adjustedmentPosition.x, playerPos.y + adjustedmentPosition.y);
		g2d.rotate(angleDirection);
		
		sprite.draw(g2d);
		
		g2d.setTransform(old);
		
	}
	
	@Override
	public void update() {
		
		SwingUtilities.convertPointFromScreen(mousePos = MouseInfo.getPointerInfo().getLocation(), canvas);
		
		angleDirection = (Math.atan2((mousePos.y - (playerPos.y + adjustedmentPosition.y)), (mousePos.x - (playerPos.x + adjustedmentPosition.x))));
		
		if (isFlipped == 0) {
			if 		(Math.abs(angleDirection) > Math.PI / 2) {
				isFlipped = 1;
			}
			else	{
				isFlipped = 2;
				sprite.flip();
			}
		}
		
		hitBox.setAngle(angleDirection);
		
		// Verifico se � a destra e se prima lo sprite � flippato
		if (Math.abs(angleDirection) <= Math.PI / 2 && isFlipped == 2) {
			sprite.flip();
			isFlipped = 1;
		}
		// Verifico se � a sinistra e se prima lo sprite non � flippato
		else if (Math.abs(angleDirection) > Math.PI / 2 && isFlipped == 1) {
			sprite.flip();
			isFlipped = 2;
		}
	
		
	}

	@Override
	public void hit(ServerData hit) {
		
		
		
	}

	public void addOnShoot(Consumer<Bullet> event) {
		
		onGunShot.add(event);
		
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