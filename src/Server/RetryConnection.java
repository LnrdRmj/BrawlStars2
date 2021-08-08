package Server;

import java.net.Socket;
import java.sql.Time;
import java.util.function.Consumer;

public class RetryConnection implements Runnable{
	
	private String host;
	private int port;
	private Consumer<Socket> toDoWhenConnected;
	private int tryTime;
	
	private Socket toConnect;
	
	public RetryConnection(String host, int port, Consumer<Socket> toDoWhenConnected, int tryTime) {

		this.host = host;
		this.port = port;
		this.toDoWhenConnected = toDoWhenConnected;
		this.tryTime = tryTime;
		
		new Thread(this).start();
	
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				
				toConnect = new Socket(host, port);
				
				toDoWhenConnected.accept(toConnect);
				
				break;
				
			} catch(Exception e) {
				
				System.out.println("Sto riprovando a riconnettermi col server");
				wait(tryTime);
				
				continue;
				
			}
			
		}
		
	}
	
	private void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
