package messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Server.HTTPMessage.HTTPMessage;

public class Broker {

	protected Map<String, List<Subscriber>> subscriberLists;

	public Broker() {
		super();
	}

	public void sendMessage(String tipo, HTTPMessage<?> messaggio) {
		
		initilizeIfListOfSubsNotExistFor(tipo);
		
		for (Subscriber sub : subscriberLists.get(tipo)) {
			
			sub.receivedMessage(tipo, messaggio);
			
		}
		
	}

	public void registerSubscribe(String tipo, Subscriber sub) {
		
		initilizeIfListOfSubsNotExistFor(tipo);
		
		subscriberLists.get(tipo).add(sub);
		
	}

	private void initilizeIfListOfSubsNotExistFor(String tipo) {
		
		if (subscriberLists.get(tipo) == null)
			subscriberLists.put(tipo, new ArrayList<>());
		
	}

}