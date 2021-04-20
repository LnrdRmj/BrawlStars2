package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import Animation.Animator;
import Collision.HitBox;
import Collision.PVector;
import Graphic.Canvas;
import Graphic.Frame;
import Utils.Force;
import Utils.Friction;
import Utils.StringUtils;

public class Player extends GameObject implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PVector pos = new PVector(0, 0);
	private int health = 100;
	
	private Dimension thick = new Dimension(25, 25);
	private int thicc = 25;
	
	private final float MAX_VELOCITY = 5;
	private PVector velocity;
	private final float ACCELERATION = 0.3f;
	
	private boolean w = false, a = false, s = false, d = false;
	
	private Force forces[];
	
	private Gun gun;
	
	private Animator animator;
	
	private Vector<String> inputsPressed;
	
	public Player(Canvas canvas){
		
		super();
		
		setName("Giocatore");
		
		canvas.addKeyListener(this);
		
		thicc = 25;
		pos = new PVector(Frame.gameWidth / 2 - thicc / 2, Frame.gameHeight / 2 - thicc / 2);
		health = 100;
		
		animator = new Animator(pos, "Sprites/17.png");
		
		this.velocity = new PVector(0, 0);
		
		// Sets the hitbox
		setHitBox(new HitBox(thick, pos, 0d));
		
		forces = new Force [4];
		
		for (int i = 0; i < forces.length; ++i)
			forces[i] = new Force();
		
		gun = new Gun(pos);
		canvas.addMouseListener(gun);
		
		inputsPressed = new Vector<String>();
		
	}
	
	public void draw(Graphics g) {
		
		hitBox.draw(g);
		
		animator.drawFrame(g);
		
		update();
		
	}
	
	public void update() {
		
		this.velocity.reset();
		
		applyForcesToVelocity();
		
		applyFrictionTovelocity();
		
		this.pos.add(velocity);
		
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
				forces[0] = new Force(new PVector(0, -MAX_VELOCITY), new PVector(0, -ACCELERATION));
				w = true;
				
				animator.start(Animator.INDIETRO);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}	
			
			break;
		case 'a':
			
			if (!a) {
				forces[1] = new Force(new PVector(-MAX_VELOCITY, 0), new PVector(-ACCELERATION, 0));
				a = true;
				
				animator.start(Animator.SINISTRA);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}
			
			break;
		case 's':
			
			if (!s) {
				forces[2] = new Force(new PVector(0, MAX_VELOCITY), new PVector(0, ACCELERATION));
				s = true;
				
				animator.start(Animator.AVANTI);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
			}
			
			break;
		case 'd':
			
			if (!d) {
				forces[3] = new Force(new PVector(MAX_VELOCITY, 0), new PVector(ACCELERATION, 0));
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
			
			forces[0] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[0].getVector()), forces[0].getVector());
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
			
			forces[1] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[1].getVector()), forces[1].getVector());
			a = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0)
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				animator.start(Animator.WASDtoDirection(inputsPressed.lastElement()));
			else 
				animator.stop();
			
			break;
		case 's':
			
			forces[2] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[2].getVector()), forces[2].getVector());
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
			
			forces[3] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[3].getVector()), forces[3].getVector());
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
	
	public void applyForcesToVelocity() {
		
		for (int i = 0; i < forces.length; ++i)
			this.velocity.add(forces[i].applyForce());
		
	}
	
	public void applyFrictionTovelocity() {
		
		this.velocity.add(Friction.calculateFriction(this.velocity));
		
	}
	
	// Getters e Setters inutili (unless...)
	public PVector getPos() {
		return this.pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
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
		return thicc;
	}

	public void setThicc(int thicc) {
		this.thicc = thicc;
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
	
}
