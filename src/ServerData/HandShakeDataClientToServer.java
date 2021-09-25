package ServerData;

import java.io.Serializable;

import Collision.PVector;

public class HandShakeDataClientToServer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3292388186846326137L;
	
	private PlayerData playerData;

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}
	
}
