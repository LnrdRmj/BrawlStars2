package Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;

import GameObjects.GameObject;

public class CollisionEngine {

	public static Vector<GameObject> toRender = new Vector<>();
	
	public static void addGameObject(GameObject g) {
		toRender.add(g);
	}
	
	public static void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		for (GameObject i : toRender)
			i.draw(g);
		
	}
	
}
