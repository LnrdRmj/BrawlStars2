package GameObjects.Player;

import java.awt.Dimension;

import Animation.Animator;
import Collision.HitBox;
import Collision.PVector;
import GameObjects.ServerData;
import Graphic.Frame;

public abstract class Player extends ServerData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int code;
	
	protected PVector pos = new PVector(0, 0);
	protected int health = 100;
	
	protected Dimension thick;
	protected int height;
	
	protected final float MAX_VELOCITY = 10;
	protected final float ACCELERATION = 0.3f;
	
	protected PVector velocity;
	protected PVector acc;
	
	protected Animator animator;
	
	public Player() {
		
		setName("Giocatore");
		
		velocity = new PVector();
		acc = new PVector();
		
		height = 125;
		pos = new PVector(Frame.gameWidth / 2 - height / 2, Frame.gameHeight / 2 - height / 2);
		health = 100;
		
		animator = new Animator(pos, "Sprites/character/17.png");
		animator.setHeightMaintainRatio(height);
		setHitBox(new HitBox(thick = new Dimension(animator.getWidthFrame(), animator.getHeightFrame()), pos));
	
	}
	
	public Player(int x, int y) {
		
		this();
		
		setPos(x, y);
		
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
}
