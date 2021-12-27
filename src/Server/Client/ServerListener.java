package Server.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Graphic.Game;
import Server.HTTPMessage.HTTPEvent;
import Server.HTTPMessage.HTTPMessage;
import messages.Publisher;

public class ServerListener implements Runnable{

	private HTTPEvent httpEvent;
	private ObjectInputStream in;
	private Thread thisThread;

	private Publisher gameObjecMessagePublisher;
	
	public ServerListener(ObjectInputStream in, HTTPEvent httpEvent) {
		
		this.httpEvent = httpEvent;
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
