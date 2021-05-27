package Server;

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
		out = new PrintWriter(this.player.getOutputStream());
		in = new BufferedReader(new InputStreamReader(this.player.getInputStream()));
		
		thisThread = new Thread(this);
		thisThread.start();
		
	}
	
	@Override
	public void run() {

		while(true) {
			
			try {
				
				String comand = in.readLine();
				
				// pos (x,y)
				String [] pos = comand.split(";");
				x = Integer.parseInt(pos[0]);
				y = Integer.parseInt(pos[1]);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
		
	}
	
	public void write(String info) {
		
		out.write(info);
		
	}

	public String getInfo() {
		
		return x + ";" + y;
		
	}
	
}
