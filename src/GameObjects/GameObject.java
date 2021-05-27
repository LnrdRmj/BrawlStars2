package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import Collision.HitBox;
import Graphic.Game;
import Graphic.Sprite;

public abstract class GameObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5705047180549861844L;
	
	protected HitBox hitBox;
	protected Color fillColor;
	protected String name;
	protected Sprite sprite;
	
	public GameObject (HitBox hitBox) {
		this();
		this.hitBox = hitBox;
	}
	
	public GameObject (Color fillColor) {
		
		this();
		this.fillColor = fillColor;
		
	}
	
	public GameObject() {
		
		fillColor = Color.decode("#FFFFFF");
		Game.addGameObject(this);
		
	}
	
	public abstract void hit(GameObject hit);

	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}
	
	public HitBox getHitBox() {
		return this.hitBox;
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public Color getFillColor() {
		return fillColor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		
		hitBox.setBounds(x, y, width, height);
		
	}
	
}
