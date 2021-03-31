package GameObjects;

import java.awt.Color;
import java.awt.Graphics;

import Collision.HitBox;
import Graphic.Game;

public abstract class GameObject {

	protected HitBox hitBox;
	protected Color fillColor;
	
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
	
	public void setShape(HitBox hitBox) {
		this.hitBox = hitBox;
	}
	
	public HitBox getShape() {
		return this.hitBox;
	}
	
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public Color getFillColor() {
		return fillColor;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public void setBounds(int x, int y, int width, int height) {
		
		hitBox.setBounds(x, y, width, height);
		
	}
	
	public HitBox getHitBox() {
		return hitBox;
	}
	
	public abstract void hit(GameObject hit);
	
}
