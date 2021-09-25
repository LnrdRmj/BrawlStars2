package Server.Server.GameObjects;

import java.io.ObjectOutputStream;

import Collision.HitBox;
import GameObjects.ServerData;
import Server.HTTPMessage;

public abstract class ServerGameObject {

	protected ServerData serverData;
	protected ObjectOutputStream out;
	protected boolean isDead = false;
	
	public ServerGameObject(ObjectOutputStream outStream) {
		super();
		this.out = outStream;
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
