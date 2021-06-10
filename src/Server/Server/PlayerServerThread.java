package Server.Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import Server.HTTPMessage;

public class PlayerServerThread implements Runnable {

	private Socket player;

	public static int codeGen = 1;
	private int code;

//	private PrintWriter out;
//	private BufferedReader in;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Integer x;
	private Integer y;

	private Thread thisThread;
	private boolean closed = false;

	public PlayerServerThread(Socket newPlayer) throws IOException {

		this.player = newPlayer;

		code = codeGen;
		codeGen++;

//		out = new PrintWriter(this.player.getOutputStream(), true);
//		in = new BufferedReader(new InputStreamReader(this.player.getInputStream()));
		out = new ObjectOutputStream(newPlayer.getOutputStream());
		in = new ObjectInputStream(newPlayer.getInputStream());
		
		thisThread = new Thread(this);
		thisThread.start();

	}

	@Override
	public void run() {

//		System.out.println("Server - Ho appena scritto al client");
//		out.println("hey sono i server piacere di conoscerti");

		while (true) {

			try {

				HTTPMessage<?> comand = (HTTPMessage<?>) in.readObject();

				switch(comand.getComand()) {
				
				case "playerPos":
					
//					System.out.println("Server - ho ricevuto un messaggio dal client");
//					System.out.println("Server - " + comand);

					// pos (x,y)
					String[] pos = ((String)comand.getMessageBody()).split(";");
					x = (int) Double.parseDouble(pos[0]);
					y = (int) Double.parseDouble(pos[1]);
					
					break;
				
				}
				
			} catch (SocketException | EOFException e) {
				close();
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

	}

	public void close() {

		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		closed = true;

	}

	public void writeInfo() {

		try {
			
			out.writeObject(new HTTPMessage<>("playerPos", getInfo()));
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public String getInfo() {

		return x + ";" + y + ";" + code;

	}

	public boolean isClosed() {
		return closed;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void write(HTTPMessage<?> info) {
		
		try {
			out.writeObject(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
