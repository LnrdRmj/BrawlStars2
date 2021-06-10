package Server.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Server.HTTPEvent;
import Server.HTTPMessage;

public class ServerListener implements Runnable{

	private HTTPEvent httpEvent;
//	private BufferedReader in;
	private ObjectInputStream in;
	private Thread thisThread;
	
	public ServerListener(Socket toListen, HTTPEvent httpEvent) {
		
		try {
			
//			in = new BufferedReader(new InputStreamReader(toListen.getInputStream()));
			in = new ObjectInputStream(toListen.getInputStream()); 
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		this.httpEvent = httpEvent;
		
		thisThread = new Thread(this);
		thisThread.start();
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				
				HTTPMessage<?> s = (HTTPMessage<?>) in.readObject();
//				System.out.println("Client - ho letto una stringa " + s);
				httpEvent.onMessageReceived(s);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
	
}
