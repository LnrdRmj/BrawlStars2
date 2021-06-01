package Server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServer {

	public static void main(String[] args) throws IOException {

		// Questo è il server in sè per sè
		try(ServerSocket serverSocket = new ServerSocket(7777)){
			
			GameMaster gameMaster = new GameMaster();
			System.out.println("Aspetto le connessioni");
			
			while(true) {
				
				Socket newPlayer = serverSocket.accept();
				gameMaster.addPlayerThread(new PlayerServerThread(newPlayer));
				
				System.out.println("Server - Si è connesso un nuovo player: " + gameMaster.numPlayers());
				
//				if (gameMaster.numPlayers() == 2) return;
			
			}
		}
	}

	public static void firstMain() throws IOException {

		// Questo è il server in sè per sè
		ServerSocket serverSocket = new ServerSocket(7777);

		System.out.println("Aspetto connessione");
		// Questo è invece il client che dovrebbe essere il mio player
		Socket player1 = serverSocket.accept();

		try (PrintWriter out = new PrintWriter(player1.getOutputStream())) {

			out.write("Mamma mia oh");

		}

		System.out.println("Primo player connesso");

		Socket player2 = serverSocket.accept();
		System.out.println("Secondo player connesso");

		serverSocket.close();

	}

}
