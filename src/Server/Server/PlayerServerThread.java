package Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerServerThread implements Runnable{

	private Socket player;
	private Thread thisThread;
	private PrintWriter out;
	private BufferedReader in;
	private Integer x;
	private Integer y;
	
	public PlayerServerThread(Socket newPlayer) throws IOException {
		
		this.player = newPlayer;
		out = new PrintWriter(this.player.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(this.player.getInputStream()));
		
		thisThread = new Thread(this);
		thisThread.start();
		
	}
	
	@Override
	public void run() {

		while(true) {
			
			try {
				
				String comand = in.readLine();
				
				System.out.println("Server - ho ricevuto un messaggio dal client");
				System.out.println("Server - " + comand);
				
				// pos (x,y)
				String [] pos = comand.split(";");
				x = (int)Double.parseDouble(pos[0]);
				y = (int)Double.parseDouble(pos[1]);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		
	}
	
	public void write(String info) {
		
		out.println(info);
		
	}

	public String getInfo() {
		
		return x + ";" + y;
		
	}
	
}
