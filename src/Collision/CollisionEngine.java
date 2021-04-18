package Collision;

import java.util.Vector;

import GameObjects.GameObject;

public class CollisionEngine {

	private static Vector<GameObject> gameObjects = new Vector<>();
	private static Vector<GameObject> toRemove 	  = new Vector<>();
	private static Vector<GameObject> toAdd 	  = new Vector<>();
	
	public static void addGameObject(GameObject toAdd) {
		CollisionEngine.toAdd.add(toAdd);
	}
	
	public static void calculateCollision() {
		
		for (GameObject obj :  gameObjects) {
			HitBox hb = obj.getHitBox();
			
			for (GameObject obj2 :  gameObjects) {
				HitBox hb2 = obj2.getHitBox();
				
				//System.out.println(obj.getName() + " - " + obj2.getName());
				
				if (hb != null && hb2 != null && obj != obj2 && hb.collide(hb2)) {
					
					//System.out.println(obj.getName() + " ha colpito " + obj2.getName());
					
					obj.hit(obj2);
				}
			}
		}
		
		if (toRemove.size() > 0) {
			gameObjects.removeAll(toRemove);
			toRemove.clear();
		}
		
		if (toAdd.size() > 0) {
			gameObjects.addAll(toAdd);
			toAdd.clear();
		}
		
	}

	public static void removeGameObject(GameObject go) {
		
		toRemove.add(go);
		
	}
	
}
