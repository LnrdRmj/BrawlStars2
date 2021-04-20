package Graphic;

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
	private static Vector<GameObject> toAdd = new Vector<>();

	public static void addGameObjectToRender(GameObject g) {
		toAdd.add(g);
	}
	
	public static void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.white);
		
		for (Iterator<GameObject> iterator = toRender.iterator(); iterator.hasNext();) {
		    GameObject go = iterator.next();
		    go.draw(g);
		}
		
		if (toRemove.size() > 0) {
			toRender.removeAll(toRemove);
			toRemove.clear();
		}
		
		if (toAdd.size() > 0) {
			toRender.addAll(toAdd);
			toAdd.clear();
		}
	}

	public static void removeGameObjectToRender(GameObject go) {

		// Non posso rimuovere subito dal vettore toRender perché sennò potrei
		// ottenere un java.util.ConcurrentModificationException
		toRemove.add(go);
		
	}
	
}
