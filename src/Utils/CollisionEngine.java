package Utils;

import java.awt.Graphics;
import java.util.Vector;

import GameObjects.GameObject;

public class CollisionEngine {

	private static Vector<GameObject> gameObjects = new Vector<>();
	
	public static void addGameObject(GameObject g) {
		gameObjects.add(g);
	}
	
	public static void calculateCollision(Graphics g) {
		
		for (GameObject obj :  gameObjects)
			for (GameObject obj2 :  gameObjects)
				if (obj != obj2 && obj.collide(obj2)) {
					obj.hit(obj2);
				}
		
	}
	
}
