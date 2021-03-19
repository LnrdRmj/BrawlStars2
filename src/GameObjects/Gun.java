package GameObjects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.SwingUtilities;

import Graphic.Frame;
import Utils.PVector;
import Utils.Renderer;
import Utils.Toast;

public class Gun extends GameObject implements MouseListener{
	
	public static Dimension gunDimension = new Dimension(100, 5);
	
	private PVector playerPos;
	private double angleDirection;
	
	Point mousePos;
	
	public Gun(PVector p) {
		playerPos = p;
		
		angleDirection = 0;
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		Renderer.addGameObjectToRender(new Bullet((int)playerPos.x, (int)playerPos.y, angleDirection));
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		shoot(e.getX(), e.getY());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
//		Toast.setText("angleDirection (radians) = " + (angleDirection));
		
		AffineTransform old = g2d.getTransform();
		g2d.translate(playerPos.x, playerPos.y);
		g2d.rotate(angleDirection);
		
		g2d.fill(new Rectangle((int)gunDimension.getWidth(), (int)gunDimension.getHeight()));
		
        g2d.setTransform(old);
        g2d.translate(0, 0);
		
        update();
        
	}
	
	public void update() {
		
		SwingUtilities.convertPointFromScreen(mousePos = MouseInfo.getPointerInfo().getLocation(), Frame.game);
		
		angleDirection = (Math.atan2((mousePos.y - playerPos.y), (mousePos.x - playerPos.x)));
		
	}
	
}
