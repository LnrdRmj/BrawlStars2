package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;

import Collision.HitBox;
import Collision.PVector;

public abstract class GameObject {

	protected HitBox hitBox;
	protected String name = "";
	
	public abstract void hit(GameObject hit);

	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public GameObject (String name) {
		this.name = name;
		hitBox = new HitBox(new Dimension(), new PVector());
	}
	
	public GameObject (HitBox hitBox) {
		this.hitBox = hitBox;
	}
	
	public GameObject (HitBox hitBox, String name) {
		this.hitBox = hitBox;
		this.name = name;
	}
	
	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}
	
	public HitBox getHitBox() {
		return this.hitBox;
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
