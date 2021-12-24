package Server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Utils.HTTPMessages;
import messages.Publisher;

public class ConnectionServer {

	public static void main(String[] args) throws IOException {

		Publisher newEnemyPublisher = new Publisher();
		
		// Questo è il server in sè per sè
		try(ServerSocket serverSocket = new ServerSocket(7777)){
			
			GameMaster gameMaster = new GameMaster();
			System.out.println("Aspetto le connessioni");
			
			while(true) {
				
				Socket newPlayerConnection = serverSocket.accept();
				PlayerServerThread newPlayer = new PlayerServerThread(newPlayerConnection);
				
				gameMaster.addPlayerThread(newPlayer);
				newEnemyPublisher.publish(HTTPMessages.NEW_ENEMY, newPlayer.getMessageForClient());
				
				System.out.println("Server - Si � connesso un nuovo player: " + gameMaster.numPlayers());
				
//				if (gameMaster.numPlayers() == 2) return;
			
			}
		}
	}

}
