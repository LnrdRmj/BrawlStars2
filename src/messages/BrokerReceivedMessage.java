package messages;

import java.util.HashMap;

public class BrokerReceivedMessage extends Broker {

	private static BrokerReceivedMessage brokerInstance;
	
	private BrokerReceivedMessage() {
		
		subscriberLists = new HashMap<>();
		
	}
	
	public static BrokerReceivedMessage getInstance() {
		
		if (brokerInstance == null) {
			brokerInstance = new BrokerReceivedMessage();
		}
		
		return brokerInstance;
		
	}
	
}
