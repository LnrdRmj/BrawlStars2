package GameObjects.Player;

import java.awt.Graphics;

import Animation.Animator;
import Collision.PVector;
import GameObjects.GameObject;
import Graphic.Renderer;
import ServerData.PlayerData;
import Utils.PVectorUtil;

public class EnemyPlayer extends Player{

	public EnemyPlayer() { 
		
		setName("EnemyPlayer");
		
		animator = new Animator(pos, "Sprites/character/7.png");
		animator.setHeightMaintainRatio(height);
		
	}
	
	public EnemyPlayer(int x, int y) {
	
		this();
		
		super.setPos(x, y);
		
	}

	public EnemyPlayer(int x, int y, int code) {
		
		this();
		
		this.code = code;
		super.setPos(x, y);
		
	}
	
	public EnemyPlayer(PVector pos, Integer code) {
		
		this((int)pos.x, (int)pos.y, code);
	
	}

	public EnemyPlayer(PlayerData playerData) {
		
		super();
		
		this.code = playerData.getCode();
		this.direction = playerData.getDirection();
		this.pos = PVectorUtil.PVectorFromString(playerData.getPos());
		
		animator = new Animator(this.pos, "Sprites/character/7.png");
		animator.setHeightMaintainRatio(height);
		
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
