package GameObjects.Player;

import java.awt.event.KeyEvent;

import Graphic.Canvas;
import Server.Client.PlayerListenerThread;

public class EnemyPlayer extends MainPlayer{

	private PlayerListenerThread listenerThread;
	
	public EnemyPlayer(Canvas canvas) {
		super(canvas);		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {}
}
