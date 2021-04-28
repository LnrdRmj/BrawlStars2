package ParticleSystem;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.util.Vector;

import ParticleSystem.particleSystems.ParticleSystemInterface;

public class ParticleSystemRenderer {

	private static Vector<ParticleSystemInterface> particleSystemToRender = new Vector<>();
	private static Vector<ParticleSystemInterface> toRemove = new Vector<>();
	private static Vector<ParticleSystemInterface> toAdd = new Vector<>();

	public static void addParticleSystem(ParticleSystemInterface g) {
		toAdd.add(g);
	}
	
	public static void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (Iterator<ParticleSystemInterface> iterator = particleSystemToRender.iterator(); iterator.hasNext();) {
			ParticleSystemInterface ps = iterator.next();
		    ps.draw(g);
		    
		    if (ps.isDead()) {
		    	
		    	toRemove.add(ps);
		    	
		    }
		    
		}
		
		if (toRemove.size() > 0) {
			particleSystemToRender.removeAll(toRemove);
			toRemove.clear();
		}
		
		if (toAdd.size() > 0) {
			particleSystemToRender.addAll(toAdd);
			toAdd.clear();
		}
	}

	public static void removeParticleSystem(ParticleSystemInterface go) {

		// Non posso rimuovere subito dal vettore toRender perché sennò potrei
		// ottenere un java.util.ConcurrentModificationException
		toRemove.add(go);
		
	}
	
}
