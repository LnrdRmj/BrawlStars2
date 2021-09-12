package ServerData;

import java.io.Serializable;

import Collision.PVector;
import Server.Server.PlayerServerThread;

public class PlayerData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8460153752312860124L;
	
	private PVector pos;
	private String direction;
	private Integer code;
	
	public PlayerData() {
		
	}
	
	public PlayerData(PlayerServerThread player) {
		
		this.pos = player.getPos();
		this.direction = player.getDirection();
		this.code = player.getCode();
		
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public PVector getPos() {
		return pos;
	}
	
	public void setPos(PVector pos) {
		this.pos = pos;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}

}
