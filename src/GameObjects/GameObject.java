package GameObjects;

import java.awt.Graphics;

public interface GameObject {

	public abstract void hit(ServerData hit);

	public abstract void draw(Graphics g);
	
	public abstract void update();
	
}
