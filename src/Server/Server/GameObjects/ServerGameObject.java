package Server.Server.GameObjects;

import java.io.ObjectOutputStream;

import Collision.HitBox;
import GameObjects.ServerData;
import Server.HTTPMessage;

public abstract class ServerGameObject {

	
	
	protected ServerData serverData;
	protected ObjectOutputStream client;
	protected boolean isDead = false;
	
	public ServerGameObject(ObjectOutputStream client) {
		super();
		this.client = client;
	}

	public abstract void update();
	
	public void hit(ServerGameObject other) {}
	
	public abstract HTTPMessage<?> getMessageForClient();
	
	public HitBox getHitBox() {
		return serverData.getHitBox();
	}

	public boolean isDead() {
		return isDead;
	}
	
}
