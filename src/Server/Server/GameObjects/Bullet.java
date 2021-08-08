package Server.Server.GameObjects;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Collision.CollisionEngine;
import Collision.HitBox;
import Collision.PVector;
import GameObjects.ServerData;
import Graphic.Frame;
import Server.HTTPMessage;
import ServerData.BulletData;
import Utils.HTTPMessages;

<<<<<<< HEAD
import static Logger.Logger.*;

public class Bullet extends ServerGameObject implements Serializable{
=======
public class Bullet extends ServerGameObject{
>>>>>>> parent of 860e9cc (Ho aggiunto una classe logger per semplicità e sembra che lo shoot dell proiettile funga in parte perché il suo stato viene aggiornato correttamente dal server ma le le informazioni del server non vengono lette correttamente dal server)
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7971045488072260238L;

	public static Dimension bulletDimension = new Dimension(30, 8);
	
	protected PVector bulletPos;
	public int bulletSpeed = 4;
	protected double angleDirection;
		
	public Bullet(PVector pos, double angleDirection, ObjectOutputStream client) {
		
		this(pos.x, pos.y, angleDirection, client);
		
	}
	
	public Bullet (float originX, float originY, double angleDirection, ObjectOutputStream client) {
		
		this((int)originX, (int)originY, angleDirection, client);
		
	}
	
	public Bullet(int originX, int originY, int mouseX, int mouseY, ObjectOutputStream client) {

		this(originX, originY, Math.atan2((mouseY - originY), (mouseX - originX)), client);

	}

	public Bullet(int originX, int originY, double angleDirection, ObjectOutputStream client) {
		
		super(client);
		
//		setName("Proiettile");
		
		bulletPos = new PVector(originX, originY);
		
		serverData = new ServerData(new HitBox(bulletDimension, bulletPos, angleDirection));
		
		this.angleDirection = angleDirection;
		
	}
	
	@Override
	public void update() {

		bulletPos.x += bulletSpeed * Math.cos(angleDirection);
		bulletPos.y += bulletSpeed * Math.sin(angleDirection);

		// If the bullet goes off-screen delete it
		if (bulletPos.x < - 100 || 
			bulletPos.x > Frame.gameWidth + 100 ||
			bulletPos.y < -100 ||
			bulletPos.y > Frame.gameHeight) {
			
			isDead = true;
			
			CollisionEngine.removeGameObject(this);
			
		}
		
		serverData.getHitBox().update();
<<<<<<< HEAD
<<<<<<< HEAD
//		logServer(bulletPos.toString());
=======
>>>>>>> parent of 860e9cc (Ho aggiunto una classe logger per semplicità e sembra che lo shoot dell proiettile funga in parte perché il suo stato viene aggiornato correttamente dal server ma le le informazioni del server non vengono lette correttamente dal server)
=======
>>>>>>> parent of 860e9cc (Ho aggiunto una classe logger per semplicità e sembra che lo shoot dell proiettile funga in parte perché il suo stato viene aggiornato correttamente dal server ma le le informazioni del server non vengono lette correttamente dal server)
		
		try {
			client.writeObject(new HTTPMessage<>(HTTPMessages.DRAW_BULLET, new BulletData(bulletPos, angleDirection, bulletPos.x + ";" + bulletPos.y)));
			client.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
