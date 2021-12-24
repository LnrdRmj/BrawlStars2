package Server.HTTPMessage;

import java.io.Serializable;

public interface HTTPEvent extends Serializable{

	public void onMessageReceived(HTTPMessage<?> message);
	
}
