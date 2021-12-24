package messages;

import java.io.Serializable;

import Server.HTTPMessage.HTTPMessage;

public interface Subscriber {

	public void receivedMessage(String type, HTTPMessage<?> m);
	
}
