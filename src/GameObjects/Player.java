package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Graphic.Game;
import Utils.Force;
import Utils.Friction;
import Utils.PVector;
import Utils.Renderer;

public class Player extends GameObject implements KeyListener{

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
	
	public Player(){
		
		super();
		
		pos = new PVector(0, 0);
		health = 100;
		thicc = 25;
		
		this.velocity = new PVector(0, 0);
		
		// Sets the hitbox
		super.setShape(new HitBox(thick, pos, 0));
		
		forces = new Force [4];
		
		for (int i = 0; i < forces.length; ++i)
			forces[i] = new Force();
		
		gun = new Gun(pos);
		
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		hitBox.draw(g);
		
		update();
		
	}
	
	public void update() {
		
		this.velocity.reset();
		
		applyForcesToVelocity();
		
		applyFrictionTovelocity();
		
		this.pos.add(velocity);
		
		//((Rectangle)hitBox).setBounds((int)pos.x, (int)pos.y, thicc, thicc);
		
	}
	
	// KeyEvent handlers
	@Override
	public void keyPressed(KeyEvent e) {
		
//		System.out.println(e.toString());
		System.out.println("Hai premuto " + e.getKeyChar());
		
		switch(e.getKeyChar()) {
		case 'w':
			
			if (!w) {
				forces[0] = new Force(new PVector(0, -MAX_VELOCITY), new PVector(0, -ACCELERATION));
				w = true;
			}	
			
			break;
		case 'a':
			
			if (!a) {
				forces[1] = new Force(new PVector(-MAX_VELOCITY, 0), new PVector(-ACCELERATION, 0));
				a = true;
			}
			
			break;
		case 's':
			
			if (!s) {
				forces[2] = new Force(new PVector(0, MAX_VELOCITY), new PVector(0, ACCELERATION));
				s = true;
			}
			
			break;
		case 'd':
			
			if (!d) {
				forces[3] = new Force(new PVector(MAX_VELOCITY, 0), new PVector(ACCELERATION, 0));
				d = true;
			}
			
			break;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
//		System.out.println("Hai rilasciato " + e.getKeyChar());
		
		switch(e.getKeyChar()) {
		case 'w':
			
			forces[0] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[0].getVector()), forces[0].getVector());
			w = false;
			break;
		case 'a':
			
			forces[1] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[1].getVector()), forces[1].getVector());
			a = false;
			break;
		case 's':
			
			forces[2] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[2].getVector()), forces[2].getVector());
			s = false;
			break;
		case 'd':
			
			forces[3] = new Force(new PVector(0, 0), Friction.calculateFriction(forces[3].getVector()), forces[3].getVector());
			d = false;
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
	
}
