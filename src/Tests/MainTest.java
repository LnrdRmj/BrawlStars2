package Tests;

import Server.HTTPMessage.HTTPMessage;

public class MainTest implements Runnable {

	public MainTest() {
		
//		Object obj  = new HTTPMessage<String> ("ciao", "ciao2");
//
//		System.out.println(obj instanceof HTTPMessage);
		
	}

	public static void main(String[] args) {

		// Calcola quanto tempo passa (in ms) dall'invio di un messaggio dal client
		// alla ricezione del messagio dal server
		new MainTest();

	}

	private  void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
