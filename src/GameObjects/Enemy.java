package GameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import Utils.PVector;

public class Enemy extends GameObject {

	private PVector pos;
	
	public Enemy() {
		
		super(new HitBox(new Dimension(50, 50), new PVector(400, 600)));
		
		setFillColor(Color.decode("#7F95D1"));
		pos = new PVector(400, 600);
		
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(fillColor);
		g.fillRect((int)pos.x, (int)pos.y, 50, 50);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit(GameObject hit) {
		// TODO Auto-generated method stub

	}

}
