package GameObjects;

import java.awt.Color;
import java.awt.Graphics;

import Graphic.Game;

public abstract class GameObject {

	protected HitBox hitBox;
	protected Color fillColor;
	
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
	
	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public void setBounds(int x, int y, int width, int height) {
		
		hitBox.setBounds(x, y, width, height);
		
	}

	public boolean collide(GameObject obj2) {
		// TODO Auto-generated method stub
		return false;
	}

	public void hit(GameObject obj2) {
		
	}
	
}
