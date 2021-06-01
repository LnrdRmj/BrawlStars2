package Server.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import GameObjects.Player.MainPlayer;

public class PlayerListenerThread implements Runnable{

	private Socket server;
	private BufferedReader in;
	private MainPlayer player;
	
	public PlayerListenerThread(Socket server, MainPlayer player){
		
		this.server = server;
		this.player = player;
		
		try {
			in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {

		while(true) {
			
			String fromServer;
			
			try {
				
				fromServer = in.readLine();
				String [] data = fromServer.split(";");
				
				int x = Integer.parseInt(data[0]);
				int y = Integer.parseInt(data[1]);
				
				player.setPos(x, y);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
}
