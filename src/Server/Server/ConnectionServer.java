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
				
				System.out.println("Server - Si � connesso un nuovo player: " + gameMaster.numPlayers());
				
//				if (gameMaster.numPlayers() == 2) return;
			
			}
		}
	}

}
