package ServerData;

import java.io.Serializable;

import Collision.PVector;
import GameObjects.Player.Player;
import Server.Server.PlayerServerThread;

public class PlayerData implements BasicData, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8460153752312860124L;
	
	private String pos;
	private String direction;
	private Integer code;
	
	public PlayerData() {
		
	}
	
	public PlayerData(Player player) {
		
		this.pos = player.getPos().x + ";" + player.getPos().y;
		this.direction = player.getDirection();
		this.code = player.getCode();
		
	}
	
	public PlayerData(PlayerServerThread player) {
		
		this.pos = player.getPos().x + ";" + player.getPos().y;
		this.direction = player.getDirection();
		this.code = player.getCode();
		
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getPos() {
		return pos;
	}
	
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "PlayerData [pos=" + pos + ", direction=" + direction + ", code=" + code + "]";
	}
	
	

}
