package Tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainTest implements Runnable {

	public MainTest() {
		
		new Thread(this).start();

		try (	Socket client = new Socket("localhost", 7777);
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				ObjectOutputStream outObj = new ObjectOutputStream(client.getOutputStream())) {

			out.println(System.currentTimeMillis() + "");
			
			wait(100);
			
			System.out.println("Ho scritto");
			outObj.writeObject(new GameObject("ciao"));
			outObj.flush();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		// Calcola quanto tempo passa (in ms) dall'invio di un messaggio dal client
		// alla ricezione del messagio dal server
		new MainTest();

	}

	@Override
	public void run() {

		try (ServerSocket server = new ServerSocket(7777);
				Socket client = server.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				ObjectInputStream inObj = new ObjectInputStream(client.getInputStream())) {
			
			long clientTime = Long.parseLong(in.readLine());
			long serverTime = System.currentTimeMillis();

			System.out.println("Tempo trascorso: " + (serverTime - clientTime) + "ms");

			wait(100);
			
			Object gameObject = inObj.readObject();
			System.out.println(gameObject.toString());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	private  void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
