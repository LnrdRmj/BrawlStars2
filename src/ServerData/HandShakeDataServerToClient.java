package ServerData;

import java.io.Serializable;

import Server.Config;

public class HandShakeDataServerToClient implements BasicData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5037512366859644432L;
	
	private Config config;
	private Integer code;
	
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

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		
		return this.getClass().getSimpleName() + "[\n"
				
				+ "Config: " + config.toString() + "\n"
				+ "Code: " + code.toString() + "\n"
				
				+ "]";
		
	}
	
}
