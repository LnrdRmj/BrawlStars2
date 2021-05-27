package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProvaPlayer {

	public static void main(String [] args) throws UnknownHostException, IOException {
		
		try(Socket s = new Socket("localhost", 7777);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream())) ){
			
			System.out.println(in.readLine());
			
		}
		
		System.out.println("Fatto");
		
	}
	
}
