package GameObjects.Player;

import java.awt.Graphics;

import Animation.Animator;
import Collision.PVector;
import GameObjects.GameObject;
import Graphic.Renderer;
import ServerData.BasicData;
import ServerData.PlayerData;
import Utils.PVectorUtil;
import messages.Subscriber;

public class EnemyPlayer extends Player{

	private static Subscriber newEnemySub;
	
	static {
		
		// Questo è il sub che crea nemici
		newEnemySub = (tipo, messaggio) -> {
			
			if (!(messaggio.getMessageBody() instanceof PlayerData)) return;
			
			PlayerData enemyData = (PlayerData) messaggio.getMessageBody();
			
			new EnemyPlayer(enemyData);
			
		};
		
	}
	
	private Subscriber enemyPlayerSub;
	
	public EnemyPlayer() { 
		
		super();
		
		setName("EnemyPlayer");
		
		animator = new Animator(pos, "Sprites/character/7.png");
		animator.setHeightMaintainRatio(height);
		
		initializeSubs();
		
		
	}
	
	private void initializeSubs() {

		this.enemyPlayerSub = (tipo, messaggio) -> {
			
			if (!(messaggio.getMessageBody() instanceof PlayerData)) return;
			
			PlayerData enemyData = ((PlayerData)messaggio.getMessageBody()); 
			
			if (enemyData.getCode().equals(this.getCode())) {
				
				this.applyData(enemyData);
				
			}
			
		};
		
	}

	public EnemyPlayer(PlayerData playerData) {
		
		this();
		
		this.code = playerData.getCode();
		this.direction = playerData.getDirection();
		this.pos = playerData.getPos();

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
		
		this.setPos(playerData.getPos());
		this.setDirection(playerData.getDirection());
		
	}

	@Override
	public void applyData(BasicData data) {
		
	}
	
}
