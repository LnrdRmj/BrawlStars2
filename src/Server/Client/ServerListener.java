package Server.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Graphic.Game;
import Server.HTTPEvent;
import Server.HTTPMessage;

public class ServerListener implements Runnable{

	private HTTPEvent httpEvent;
	private ObjectInputStream in;
	private Thread thisThread;
	
	public ServerListener(ObjectInputStream in, HTTPEvent httpEvent) {
		
		this.httpEvent = httpEvent;
		this.in = in;
		
		thisThread = new Thread(this);
		thisThread.start();
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				
				HTTPMessage<?> s = (HTTPMessage<?>) in.readObject();
				
				httpEvent.onMessageReceived(s);
				
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
