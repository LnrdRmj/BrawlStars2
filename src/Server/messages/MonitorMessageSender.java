package Server.messages;

public class MonitorMessageSender {

	private boolean inUse = false;
	
	synchronized public void startSend() {
		
		while(inUse) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		inUse = true;
		
	}
	
	synchronized public void endSend() {
		
		inUse = false;
		
		notifyAll();
		
	}
	
}
