package messages;

import Server.HTTPMessage.HTTPMessage;

public class Publisher {

	public void publish(String tipo, HTTPMessage<?> messaggio) {
		
		Broker.getInstance().sendMessage(tipo, messaggio);
		
	}
	
}
