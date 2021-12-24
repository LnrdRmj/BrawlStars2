package Server.messages;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MessageSender {

	private static MessageSender instance;
	
	private List<ObjectOutputStream> streams;
	private List<MonitorMessageSender> monitors;
	
	private MessageSender() {
		
		this.streams = new ArrayList<>();
		
	}
	
	public static MessageSender getInstance() {
		
		if (instance == null)
			instance = new MessageSender();
			
		return instance;
		
	}
	
	public void addOutStream(ObjectOutputStream newOutStream) {
		
		this.streams.add(newOutStream);
		
	}
	
	
	
}
