package Server.HTTPMessage;

import GameObjects.Bullets.Bullet;
import GameObjects.Player.MainPlayer;
import Server.Server.PlayerServerThread;
import ServerData.BulletData;
import ServerData.HandShakeDataClientToServer;
import ServerData.PlayerData;
import Utils.HTTPMessages;

public class HTTPMessageFactory {

	public static HTTPMessage<PlayerData> getNewEnemyMessage(PlayerServerThread newPlayer){
		return new HTTPMessage<>(HTTPMessages.NEW_ENEMY, new PlayerData(newPlayer));
	}

	public static HTTPMessage<PlayerData> getNewEnemyMessage(MainPlayer mainPlayer) {
		return new HTTPMessage<>(HTTPMessages.PLAYER_DATA, new PlayerData(mainPlayer));
	}
	
	public static HTTPMessage<HandShakeDataClientToServer> getHandhakeMessage(HandShakeDataClientToServer handshake){
		return new HTTPMessage<>(HTTPMessages.HAND_SHAKE, handshake);
	}

	public static HTTPMessage<BulletData> getBulletShotMessage(Bullet bullet) {
		return new HTTPMessage<>(HTTPMessages.BULLET_SHOT, new BulletData(bullet));
	}

	public static HTTPMessage<PlayerData> updatePlayerMessage(PlayerServerThread playerServerThread) {
		return new HTTPMessage<>(HTTPMessages.PLAYER_DATA, new PlayerData(playerServerThread));
	}

	public static HTTPMessage<BulletData> updateBulletMessage(Server.Server.GameObjects.Bullets.Bullet bullet) {
		return new HTTPMessage<>(HTTPMessages.DRAW_BULLET, new BulletData(bullet));
	}

	public static HTTPMessage<PlayerData> getRemoveEnemyMessage(PlayerServerThread playerServerThread) {
		return new HTTPMessage<>(HTTPMessages.REMOVE_ENEMY, new PlayerData(playerServerThread));
	}

}
