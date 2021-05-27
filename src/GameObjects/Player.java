package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Animation.Animator;
import Collision.HitBox;
import Collision.PVector;
import GameObjects.Bullets.Bullet;
import GameObjects.Guns.Gun;
import Graphic.Canvas;
import Graphic.Frame;
import Server.SocketWriter;
import Utils.Friction;
import Utils.KeyAction;
import Utils.PVectorUtil;
import Utils.StringUtils;

public class Player extends GameObject implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PVector pos = new PVector(0, 0);
	private PVector gunPos;
	private int health = 100;
	
	private Dimension thick;
	private int height;
	
	private final float MAX_VELOCITY = 10;
	private final float ACCELERATION = 0.3f;
	
	private boolean w = false, a = false, s = false, d = false;
	
	private PVector velocity;
	private PVector acc;
	
	private Gun gun;
	private Animator animator;
	private Vector<String> inputsPressed;
	private Map<String, KeyAction> keyToAction;
	
	private Socket socket;
	private SocketWriter socketWriter;
	
	public Player(Canvas canvas){
		
		super();
		
		setName("Giocatore");
		
		canvas.addKeyListener(this);

		velocity = new PVector();
		acc = new PVector();
		
		height = 125;
		pos = new PVector(Frame.gameWidth / 2 - height / 2, Frame.gameHeight / 2 - height / 2);
		health = 100;
		
		animator = new Animator(pos, "Sprites/character/17.png");
		animator.setHeightMaintainRatio(height);
		setHitBox(new HitBox(thick = new Dimension(animator.getWidthFrame(), animator.getHeightFrame()), pos));
		
		gunPos = new PVector(pos.x + thick.getWidth() * .3, pos.y + thick.getHeight() * .3);
		gun = new Gun(gunPos);
		canvas.addMouseListener(gun);
		
		inputsPressed = new Vector<String>();
		keyToAction = new HashMap<String, KeyAction>();
		
		populateKeyAction();
		
		try {
			
			socket = new Socket("localhost", 7777);
			socketWriter = new SocketWriter(socket);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics g) {
		
//		hitBox.draw(g);
		animator.drawFrame(g);
		
		outOfWindow(g);
		
	}
	
	public void update() {
		
		velocity.add(acc);
		
		if 		(velocity.x >= MAX_VELOCITY) 	velocity.x =  MAX_VELOCITY;
		else if (velocity.x <= -MAX_VELOCITY)	velocity.x = -MAX_VELOCITY;
		if 		(velocity.y >= MAX_VELOCITY) 	velocity.y =  MAX_VELOCITY;
		else if (velocity.y <= -MAX_VELOCITY)	velocity.y = -MAX_VELOCITY;
		
		applyFrictionToVelocity();
		
		if (outOfWindow()) {
			
			stopPlayer();
//			acc.x += - acc.x * .1;
//			acc.y += - acc.y * .1;
			
		}
		else {
			
			pos.add(velocity);
			gunPos.add(velocity);
			socketWriter.write(pos.x + ";" + pos.y);
		}
		
	}
	
	// KeyEvent handlers
	@Override
	public void keyPressed(KeyEvent e) {
		
//		System.out.println(e.toString());
//		System.out.println("Hai premuto " + e.getKeyChar());
		
		char keyChar = e.getKeyChar();
		
		switch(e.getKeyChar()) {
		case 'w':
			
			if (!w) {
				acc.add(0, -ACCELERATION);
				w = true;
				
				animator.start(Animator.INDIETRO);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}	
			
			break;
		case 'a':
			
			if (!a) {
				acc.add(-ACCELERATION, 0);
				a = true;
				
				animator.start(Animator.SINISTRA);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}
			
			break;
		case 's':
			
			if (!s) {
				acc.add(0, ACCELERATION);
				
				s = true;
				
				animator.start(Animator.AVANTI);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}
			
			break;
		case 'd':
			
			if (!d) {
				acc.add(ACCELERATION, 0);
				d = true;
				
				animator.start(Animator.DESTRA);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}
			
			break;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
//		System.out.println("Hai rilasciato " + e.getKeyChar());
		
		char keyChar = e.getKeyChar();
		
		switch(keyChar) {
		case 'w':
			
			acc.add(0, ACCELERATION);
			
			w = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0)
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				animator.start(Animator.WASDtoDirection(inputsPressed.lastElement()));
			else 
				animator.stop();
			
			break;
		case 'a':
			
			keyToAction.get("a").doSomething();
			
			break;
		case 's':
			
			acc.add(0, -ACCELERATION);
			
			s = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0)
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				animator.start(Animator.WASDtoDirection(inputsPressed.lastElement()));
			else 
				animator.stop();
			
			break;
		case 'd':
			
			acc.add(-ACCELERATION, 0);
			
			d = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0)
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				animator.start(Animator.WASDtoDirection(inputsPressed.lastElement()));
			else 
				animator.stop();
			
			break;
			
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void applyFrictionToVelocity() {
		
		this.velocity.add(Friction.calculateFriction(this.velocity));
		
	}
	
	private boolean outOfWindow() {
		
		PVector p = PVectorUtil.addVectors(pos, velocity);
				
		return p.x < 0 || p.x > Frame.gameWidth - thick.width || p.y < 0 || p.y > Frame.gameHeight - thick.height;
		
	}
	
	private boolean outOfWindow(Graphics g) {
		
		PVector p = PVectorUtil.addVectors(pos, velocity);
		
		g.fillOval((int)p.x, (int)p.y, 10, 10);
				
		return p.x < 0 || p.x > Frame.gameWidth - thick.width || p.y < 0 || p.y > Frame.gameHeight - thick.height;
		
	}
	
	private void stopPlayer() {
		
		this.acc.reset();
		this.velocity.reset();
		
	}
	
	private void populateKeyAction() {
		
		keyToAction.put("a", new AAction());
		
	}
	
	// Getters e Setters inutili (unless...)
	public PVector getPos() {
		return this.pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}
	
	public void setPos(int x, int y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	public float getX() {
		return this.pos.x;
	}
	
	public float getY() {
		return this.pos.y;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getThicc() {
		return height;
	}

	public void setThicc(int thicc) {
		this.height = thicc;
	}
	
	public Gun getGun() {
		return gun;
	}

	@Override
	public void hit(GameObject hit) {
		
		if 		(hit instanceof Bullet) {
			// Fai qualcosa
		}
		else if (hit instanceof Enemy) {
			// Fai qualcos'altro
		}
		
	}
	
	private class AAction implements KeyAction{

		private char key = 'a';
		
		@Override
		public void doSomething() {
			
			acc.add(ACCELERATION, 0);
			
			a = false;
			
			inputsPressed.remove(StringUtils.charToString(key));
			
			if (inputsPressed.size() > 0)
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				animator.start(Animator.WASDtoDirection(inputsPressed.lastElement()));
			else 
				animator.stop();
			
		}
		
	}
	
}
