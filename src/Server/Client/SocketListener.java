package Server.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Graphic.Game;
import Server.HTTPMessage.HTTPEvent;
import Server.HTTPMessage.HTTPMessage;
import messages.Publisher;

public class SocketListener implements Runnable{

	private ObjectInputStream in;
	private Thread thisThread;

	private Publisher gameObjecMessagePublisher;
	
	public SocketListener(ObjectInputStream in) {
		
		this.in = in;
		
		gameObjecMessagePublisher = new Publisher();

		thisThread = new Thread(this);
		thisThread.start();
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				
				HTTPMessage<?> s = (HTTPMessage<?>) in.readObject();
				
				gameObjecMessagePublisher.publish(s.getComand(), s);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			}
			
			
		}
		
	}
	
	
	
}
