package Server.HTTPMessage;

import GameObjects.Player.MainPlayer;
import Server.Server.PlayerServerThread;
import ServerData.PlayerData;
import Utils.HTTPMessages;

public class HTTPMessageFactory {

	public static HTTPMessage<PlayerData> getNewEnemyMessage(PlayerServerThread newPlayer){
		return new HTTPMessage<>(HTTPMessages.NEW_ENEMY, new PlayerData(newPlayer));
	}

	public static HTTPMessage<?> removeEnemyMessage(PlayerServerThread player) {
		return new HTTPMessage<Integer>(HTTPMessages.REMOVE_ENEMY, player.getCode());
	}

	public static Object getNewEnemyMessage(MainPlayer mainPlayer) {
		return new HTTPMessage<>(HTTPMessages.PLAYER_DATA, new PlayerData(mainPlayer));
	}
	
}
