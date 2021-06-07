package Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class PlayerServerThread implements Runnable {

	private Socket player;

	public static int codeGen = 1;
	private int code;

	private PrintWriter out;
	private BufferedReader in;

	private Integer x;
	private Integer y;

	private Thread thisThread;
	private boolean closed = false;

	public PlayerServerThread(Socket newPlayer) throws IOException {

		this.player = newPlayer;

		code = codeGen;
		codeGen++;

		out = new PrintWriter(this.player.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(this.player.getInputStream()));

		thisThread = new Thread(this);
		thisThread.start();

	}

	@Override
	public void run() {

//		System.out.println("Server - Ho appena scritto al client");
//		out.println("hey sono i server piacere di conoscerti");

		while (true) {

			try {

				String comand = in.readLine();

//				System.out.println("Server - ho ricevuto un messaggio dal client");
//				System.out.println("Server - " + comand);

				// pos (x,y)
				String[] pos = comand.split(";");
				x = (int) Double.parseDouble(pos[0]);
				y = (int) Double.parseDouble(pos[1]);

			} catch (SocketException e) {
				close();
				break;
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
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

		out.println(getInfo());

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

	public void write(String info) {
		out.println(info);
	}

}
