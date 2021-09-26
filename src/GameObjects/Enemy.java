package GameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;
import ServerData.BasicData;
import Utils.Random;

public class Enemy extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PVector pos;
	private Double  angle;
	private Color fillColor;

	public Enemy() {
		
		super("Nemico");
		
		pos = new PVector(Random.random(Frame.gameWidth), Random.random(Frame.gameHeight));
		angle = 0d;
		setHitBox(new HitBox(new Dimension(50, 50), pos, angle));
		
		fillColor = (new Color(52, 235, Random.random(256)));
		
		setName("Nemico");
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(fillColor);
		hitBox.draw(g);
		
	}

	@Override
	public void update() {

//		angle += 0.1;
//		hitBox.setAngle(angle);

	}

	@Override
	public void hit(GameObject hit) {
		
		
		
	}

	@Override
	public void applyData(BasicData data) {
		// TODO Auto-generated method stub
		
	}

}
