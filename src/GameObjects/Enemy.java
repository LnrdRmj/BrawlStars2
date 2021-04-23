package GameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import Collision.HitBox;
import Collision.PVector;
import Graphic.Frame;
import Utils.Random;

public class Enemy extends GameObject {

	private PVector pos;
	private Double  angle;

	public Enemy() {
		
		super();
		
		pos = new PVector(Random.random(Frame.gameWidth), Random.random(Frame.gameHeight));
		angle = 0d;
		setHitBox(new HitBox(new Dimension(50, 50), pos, angle));
		
		setFillColor(new Color(52, 235, Random.random(256)));
		
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
		
		if (hit instanceof Bullet){
//			changeRandomPos();
		}
		
	}

	private void changeRandomPos() {

		this.pos.x = Random.random(0, Frame.gameWidth);
		this.pos.y = Random.random(0, Frame.gameHeight);
		
	}

}
