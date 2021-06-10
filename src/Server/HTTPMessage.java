package Server;

import java.io.Serializable;

public class HTTPMessage<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5999403392579248783L;
	
	private String comand;
	private T messageBody;
	
	public HTTPMessage(String comand, T messageBody) {
		
		this.comand = comand;
		this.messageBody = messageBody;
		
	}

	public String getComand() {
		return comand;
	}

	public T getMessageBody() {
		return messageBody;
	}
	
}
