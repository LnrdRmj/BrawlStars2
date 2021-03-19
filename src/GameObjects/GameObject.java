package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class GameObject {

	protected Rectangle hitBox;
	
	public void setShape(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
	
	public Shape getShape() {
		return this.hitBox;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update();
	
	public void setBounds(int x, int y, int width, int height) {
		
		hitBox.setBounds(x, y, width, height);
		
	}
	
}
