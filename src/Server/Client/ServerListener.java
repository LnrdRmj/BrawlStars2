package Server.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import Server.HTTPEvent;

public class ServerListener implements Runnable{

	private HTTPEvent httpEvent;
	private BufferedReader in;
	
	public ServerListener(Socket toListen, HTTPEvent httpEvent) {
		
		try {
			
			in = new BufferedReader(new InputStreamReader(toListen.getInputStream()));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		this.httpEvent = httpEvent;
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				
				httpEvent.onMessageReceived(in.readLine());
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
	
}
