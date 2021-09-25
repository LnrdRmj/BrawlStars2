package GameObjects.Player;

import java.awt.Dimension;

import Animation.Animator;
import Collision.HitBox;
import Collision.PVector;
import GameObjects.GameObject;
import Graphic.Frame;
import Graphic.Renderer;

public abstract class Player extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Integer code;
	
	protected PVector pos = new PVector(0, 0);
	protected int health = 100;
	
	protected Dimension thick;
	protected int height;
	
	protected final float MAX_VELOCITY = 10;
	protected final float ACCELERATION = 0.3f;
	
	protected PVector velocity;
	protected PVector acc;
	
	protected Animator animator;
	
	protected String direction;
	
	public Player() {
		
		super("Giocatore");
		
		velocity = new PVector();
		acc = new PVector();
		
		height = 125;
		pos = new PVector(Frame.gameWidth / 2 - height / 2, Frame.gameHeight / 2 - height / 2);
		health = 100;
		
		animator = new Animator(pos, "Sprites/character/17.png");
		animator.setHeightMaintainRatio(height);
		setHitBox(new HitBox(thick = new Dimension(animator.getWidthFrame(), animator.getHeightFrame()), pos));
	
		direction = "s";
		
		Renderer.addGameObjectToRender(this);
		
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
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public void setPos(int x, int y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	public void setPos(float x, float y) {
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public void setDirection(String direction) {
		
		if (this.direction != direction)
			if (direction.equals(" "))
				animator.stop();
			else 
				animator.start(Animator.WASDtoDirection(direction));
		
		this.direction = direction;
		
		
	}
	
}
