package GameObjects;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;

import Graphic.Canvas;
import Server.PlayerListenerThread;

public class EnemyPlayer extends Player{

	private PlayerListenerThread listenerThread;
	
	public EnemyPlayer(Canvas canvas) {
		super(canvas);
		
		try {
			
			listenerThread = new PlayerListenerThread(new Socket("localhost", 7777), this);
			new Thread(listenerThread).start();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {}
}
