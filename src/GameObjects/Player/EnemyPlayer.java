package GameObjects.Player;

import java.awt.Graphics;

import Animation.Animator;
import Collision.PVector;
import GameObjects.GameObject;
import Graphic.Renderer;
import ServerData.BasicData;
import ServerData.PlayerData;
import Utils.PVectorUtil;

public class EnemyPlayer extends Player{

	public EnemyPlayer() { 
		
		super();
		
		setName("EnemyPlayer");
		
		animator = new Animator(pos, "Sprites/character/7.png");
		animator.setHeightMaintainRatio(height);
		
	}
	
//	public EnemyPlayer(PVector pos, int code) {
//		
//		this();
//		
//		this.code = code;
//		super.setPos(pos);
//		
//	}
	
	public EnemyPlayer(PlayerData playerData) {
		
		this();
		
		this.code = playerData.getCode();
		this.direction = playerData.getDirection();
		this.pos = PVectorUtil.PVectorFromString(playerData.getPos());

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

	public void applyData(PlayerData playerData) {
		
		this.setPos(PVectorUtil.PVectorFromString(playerData.getPos()));
		this.setDirection(playerData.getDirection());
		
	}

	@Override
	public void applyData(BasicData data) {
		
	}
	
}
