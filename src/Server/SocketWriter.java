package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWriter {

	private PrintWriter out;
	
	public SocketWriter(Socket socket) {

		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void write(String data) {
		out.write(data);
		out.flush();
	}
	
}
