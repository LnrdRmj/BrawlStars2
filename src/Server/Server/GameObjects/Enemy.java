package Server.Server.GameObjects;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Collision.PVector;
import Graphic.Frame;
import Server.HTTPMessage;
import Server.Server.GameObjects.Bullets.Bullet;
import ServerData.BulletData;
import ServerData.EnemyBotData;
import Utils.HTTPMessages;
import Utils.Random;

public class Enemy extends ServerGameObject{

	public Enemy(ObjectOutputStream client) {
		super(client);
	}


	protected Dimension thick;

	private PVector pos;
	private Double  angle;
	
	public Enemy(ObjectOutputStream client, PVector pos, Double angle) {
		super(client);
		this.pos = pos;
		this.angle = angle;
	}

	
	@Override
	public void update() {

		
		
	}

	@Override
	public void hit(ServerGameObject hit) {
		
		if (hit instanceof Bullet){
			changeRandomPos();
			
			try {
				out.writeObject(new HTTPMessage<>(HTTPMessages.DRAW_BOT_ENEMY, new EnemyBotData(pos, angle)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void changeRandomPos() {

		int x = Random.random(0, Frame.gameWidth);
		int y = Random.random(0, Frame.gameHeight);
		this.hitBox.setPos(x, y);
		
	}


	@Override
	public HTTPMessage<?> getMessageForClient() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
