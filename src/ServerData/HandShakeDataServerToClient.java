package ServerData;

import java.io.Serializable;

import Server.Config;

public class HandShakeDataServerToClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5037512366859644432L;
	
	private Config config;
	private int code;
	
	public HandShakeDataServerToClient() {
		
	}
	
	public HandShakeDataServerToClient(Config config, int code) {
		
		this.config = config;
		this.code = code;
		
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public Config getConfig() {
		return config;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
