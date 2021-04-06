package Collision;

import java.util.Vector;

import GameObjects.GameObject;

public class CollisionEngine {

	private static Vector<GameObject> gameObjects = new Vector<>();
	private static Vector<GameObject> toRemove 	  = new Vector<>();
	
	public static void addGameObject(GameObject toAdd) {
		gameObjects.add(toAdd);
	}
	
	public static void calculateCollision() {
		
		for (GameObject obj :  gameObjects) {
			HitBox hb = obj.getHitBox();
			
			for (GameObject obj2 :  gameObjects) {
				HitBox hb2 = obj2.getHitBox();
				if (obj != obj2 && hb.collide(hb2)) {
					
					//System.out.println(obj.getName() + " ha colpito " + obj2.getName());
					
					obj.hit(obj2);
				}
			}
		}
		
		gameObjects.removeAll(toRemove);
		
	}

	public static void removeGameObject(GameObject go) {
		
		toRemove.add(go);
		
	}
	
}
