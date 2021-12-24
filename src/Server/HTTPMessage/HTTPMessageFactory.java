package Server.HTTPMessage;

import GameObjects.Player.MainPlayer;
import Server.Server.PlayerServerThread;
import ServerData.HandShakeDataClientToServer;
import ServerData.PlayerData;
import Utils.HTTPMessages;

public class HTTPMessageFactory {

	public static HTTPMessage<PlayerData> getNewEnemyMessage(PlayerServerThread newPlayer){
		return new HTTPMessage<>(HTTPMessages.NEW_ENEMY, new PlayerData(newPlayer));
	}

	public static HTTPMessage<Integer> removeEnemyMessage(PlayerServerThread player) {
		return new HTTPMessage<>(HTTPMessages.REMOVE_ENEMY, player.getCode());
	}

	public static HTTPMessage<PlayerData> getNewEnemyMessage(MainPlayer mainPlayer) {
		return new HTTPMessage<>(HTTPMessages.PLAYER_DATA, new PlayerData(mainPlayer));
	}
	
	public static HTTPMessage<HandShakeDataClientToServer> getHandhakeMessage(HandShakeDataClientToServer handshake){
		return new HTTPMessage<>(HTTPMessages.HAND_SHAKE, handshake);
	}
	
}
