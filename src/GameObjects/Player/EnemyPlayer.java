package GameObjects.Player;

import java.awt.Graphics;

import GameObjects.GameObject;

public class EnemyPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EnemyPlayer() {
		
		super();
		
	}

	@Override
	public void hit(GameObject hit) {
		
	}

	@Override
	public void draw(Graphics g) {
		
//		hitBox.draw(g);
		animator.drawFrame(g);
		
	}

	@Override
	public void update() {

		
		
	}
	
}
