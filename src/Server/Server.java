package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String [] args) throws IOException {
		
		// Questo è il server in sè per sè
		ServerSocket serverSocket = new ServerSocket(7777);
		
		System.out.println("Aspetto connession");
		// Questo è invece il client che dovrebbe essere il mio player
		Socket player1 = serverSocket.accept();
		System.out.println("Primo player connesso");
		Socket player2 = serverSocket.accept();
		System.out.println("Secondo player connesso");
		
		serverSocket.close();
		
	}
	
}
