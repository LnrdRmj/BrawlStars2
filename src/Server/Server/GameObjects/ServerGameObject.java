package Server.Server.GameObjects;

import java.io.ObjectOutputStream;

import Collision.HitBox;
import Server.HTTPMessage.HTTPMessage;

public abstract class ServerGameObject {

	public static Integer codeGenerator = 0;
	protected Integer code;
	protected HitBox hitBox;
	protected ObjectOutputStream out;
	protected boolean isDead = false;
	
	public ServerGameObject(ObjectOutputStream outStream) {
		
		this.out = outStream;
		code = codeGenerator++;
		
	}

	public abstract void update();
	
	public void hit(ServerGameObject other) {}
	
	public abstract HTTPMessage<?> getMessageForClient();
	
	public HitBox getHitBox() {
		return hitBox;
	}

	public boolean isDead() {
		return isDead;
	}
	
	public Integer getCode() {
		return this.code;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	
}
