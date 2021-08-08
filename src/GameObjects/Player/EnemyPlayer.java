package GameObjects.Player;

import java.awt.Graphics;

import GameObjects.GameObject;
import GameObjects.ServerData;

public class EnemyPlayer extends Player implements GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EnemyPlayer() {
		
		super();
		
	}

	@Override
	public void hit(ServerData hit) {
		
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
