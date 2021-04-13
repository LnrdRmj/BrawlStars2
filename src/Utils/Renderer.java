package Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.util.Vector;

import GameObjects.GameObject;

public class Renderer {

	private static Vector<GameObject> toRender = new Vector<>();
	private static Vector<GameObject> toRemove = new Vector<>();

	public static void addGameObjectToRender(GameObject g) {
		toRender.add(g);
	}
	
	public static void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		
		for (Iterator<GameObject> iterator = toRender.iterator(); iterator.hasNext();) {
		    GameObject go = iterator.next();
		    go.draw(g);
		}
		
		toRender.removeAll(toRemove);
		
	}

	public static void removeGameObjectToRender(GameObject go) {

		// Non posso rimuovere subito dal vettore toRender perché sennò potrei
		// ottenere un java.util.ConcurrentModificationException
		toRemove.add(go);
		
	}
	
}
