package messages;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Server.HTTPMessage.HTTPMessage;

public class MessageSender {
	
	private static MessageSender messageSender;
	private ObjectOutputStream out;
	
	private MessageSender() {
		
	}
	
	public void sendMessage(HTTPMessage<? extends Serializable> messageToSend) {
		
		try {
			out.writeObject(messageToSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static MessageSender getInstance() {
		
		if (messageSender == null) messageSender = new MessageSender();
		
		return messageSender;
		
	}
	
	public static void init(ObjectOutputStream out) {
		getInstance().setOutput(out);
	}
	
	private void setOutput(ObjectOutputStream out) {
		this.out = out;
	}
	
}
