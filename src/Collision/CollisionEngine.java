package Collision;

import java.util.Vector;

import Server.Server.GameObjects.ServerGameObject;

public class CollisionEngine {

	private static Vector<ServerGameObject> gameObjects 	= new Vector<>();
	private static Vector<ServerGameObject> toRemove 	  	= new Vector<>();
	private static Vector<ServerGameObject> toAdd 	 		= new Vector<>();
	
	public static void addGameObject(ServerGameObject toAdd) {
		CollisionEngine.toAdd.add(toAdd);
	}
	
	public static void calculateCollision() {
		
		for (ServerGameObject obj :  gameObjects) {
			HitBox hb = obj.getHitBox();
			
			for (ServerGameObject obj2 :  gameObjects) {
				HitBox hb2 = obj2.getHitBox();
				
				if (hb != null && hb2 != null && obj != obj2 && hb.collide(hb2)) {
					
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

	public static void removeGameObject(ServerGameObject go) {
		
		toRemove.add(go);
		
	}
	
}
