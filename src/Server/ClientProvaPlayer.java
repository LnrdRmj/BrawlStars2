package Server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientProvaPlayer {

	public static void main(String [] args) throws UnknownHostException, IOException {
		
		new Socket("localhost", 7777).close();
		System.out.println("Fatto");
	}
	
}
