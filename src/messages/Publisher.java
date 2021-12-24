package messages;

import Server.HTTPMessage.HTTPMessage;

public class Publisher {

	public void publish(String tipo, HTTPMessage<?> messaggio) {
		
		BrokerReceivedMessage.getInstance().sendMessage(tipo, messaggio);
		
	}
	
}
