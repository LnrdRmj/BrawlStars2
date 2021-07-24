package Server.Server.GameObjects;

import java.io.ObjectOutputStream;

import Collision.HitBox;
import GameObjects.ServerData;

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
	
	public HitBox getHitBox() {
		return serverData.getHitBox();
	}

	public boolean isDead() {
		return isDead;
	}
	
}
