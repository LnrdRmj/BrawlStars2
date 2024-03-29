package Server.Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.function.Consumer;

import Collision.PVector;
import Server.Client.SocketListener;
import Server.HTTPMessage.HTTPMessage;
import Server.HTTPMessage.HTTPMessageFactory;
import Server.Server.GameObjectUtil.BulletUtil;
import Server.Server.GameObjects.ServerGameObject;
import Server.Server.GameObjects.Bullets.NormalBullet;
import ServerData.BulletData;
import ServerData.HandShakeDataClientToServer;
import ServerData.HandShakeDataServerToClient;
import ServerData.PlayerData;
import Utils.HTTPMessages;
import Utils.PVectorUtil;
import messages.Broker;
import messages.BrokerReceivedMessage;
import messages.Subscriber;

import static Logger.Logger.*;

/*
 * Serve soltanto per la gestione della connessione col Client
 * 
 * */

public class PlayerServerThread extends ServerGameObject implements Runnable {

	private Socket player;

	public static int codeGen = 1;
	private Integer code;

	private PVector pos;
	private Integer x;
	private Integer y;

	private Thread thisThread;
	private boolean closed = false;
	
	private ObjectInputStream in;
	
	private List<Consumer<? super ServerGameObject>> onNewGameObject;
	private List<Consumer<? super ServerGameObject>> onDeadGameObject;
	
	private String direction;
	
	private Subscriber bulletShotSub;
	private Subscriber playerUpdateSub;
	
	public PlayerServerThread(Socket newPlayer) throws IOException {

		super(new ObjectOutputStream(newPlayer.getOutputStream()));
		
		in = new ObjectInputStream(newPlayer.getInputStream());
		
		code = codeGen++;
		
		// Dopo aver mandato l'handshake devo aspettare il messaggio di ritorno del client
		try {
			
			HTTPMessage<?> handShakeMessage = (HTTPMessage<?>)in.readObject();
			
			if (!(handShakeMessage.getMessageBody() instanceof HandShakeDataClientToServer)) return;
			
			HandShakeDataClientToServer handShake = (HandShakeDataClientToServer) handShakeMessage.getMessageBody();
			PlayerData playerData = handShake.getPlayerData();
			
			this.pos = playerData.getPos();
			this.direction = playerData.getDirection();
			
			HandShakeDataServerToClient handShakeData = new HandShakeDataServerToClient();
			handShakeData.setConfig(GameMaster.config);
			handShakeData.setCode(this.getCode());
			
			// Qua mando l'handShake dal server al client
			write(new HTTPMessage<>(HTTPMessages.HAND_SHAKE, handShakeData));
			
			// Dopo aver gestito l'handShake continuo col normale ciclo di vita
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		this.player = newPlayer;
		
		new SocketListener(in);

		onNewGameObject = new Vector<>();
		onDeadGameObject = new Vector<>();

		initializeSubs();
		
		BrokerReceivedMessage.getInstance().registerSubscribe(HTTPMessages.PLAYER_DATA, playerUpdateSub);
		BrokerReceivedMessage.getInstance().registerSubscribe(HTTPMessages.BULLET_SHOT, bulletShotSub);
		
//		thisThread = new Thread(this);
//		thisThread.start();
		
	}

	@Override
	public void run() {

//		while (true) {
//
//			try {
//
//				Object cmd = in.readObject();
//				
//				if (!(cmd instanceof HTTPMessage)) continue;
//				
//				HTTPMessage<?> comand = (HTTPMessage<?>) cmd;
//				
//				switch(comand.getComand()) {
//				
//				case HTTPMessages.PLAYER_DATA:
//					
//					if (!(comand.getMessageBody() instanceof PlayerData)) break;
//					
//					PlayerData pd = (PlayerData)comand.getMessageBody();
//					
////					pos = pd.getPos();
//					pos = PVectorUtil.pVectorFromString(pd.getPosString());
//					direction =  pd.getDirection();
//					
//					break;
//					
//				case HTTPMessages.BULLET_SHOT:
//					
//					if (!(comand.getMessageBody() instanceof BulletData)) break;
//					
//					BulletData bulletData = (BulletData) comand.getMessageBody();
//					
//					ServerGameObject newBullet = BulletUtil.getBullet(bulletData, out);
////					ServerGameObject newBullet = new Bullet(bulletData, out);
//					
//					onNewGameObject.forEach(el -> el.accept(newBullet));
//					
//					break;
//					
//				}
//				
//			} catch (SocketException | EOFException e) {
//				close();
//				break;
//			} catch (IOException e) {
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//				break;
//			} catch (ClassNotFoundException e) {
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//				break;
//			}
//
//		}

	}

	public void update() {
		
	}

	public void initializeSubs() {
		
		bulletShotSub = (type, message) -> {
			
			BulletData bulletData = (BulletData) message.getMessageBody();
			
			ServerGameObject newBullet = BulletUtil.getBullet(bulletData, out);
//			ServerGameObject newBullet = new Bullet(bulletData, out);
			
			onNewGameObject.forEach(el -> el.accept(newBullet));
			
		};
		
		playerUpdateSub = (type, message) -> {
			PlayerData pd = (PlayerData)message.getMessageBody();
			
//			pos = pd.getPos();
			
			if (!pd.getCode().equals(this.getCode())) return;
			
			pos = PVectorUtil.pVectorFromString(pd.getPosString());
			direction =  pd.getDirection();
		};
		
	}
	
	public void close() {

		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		closed = true;

	}

	public String getFormattedPos() {

		return x + ";" + y + ";";

	}

	public boolean isClosed() {
		return closed;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void write(HTTPMessage<?> info) {
		
		try {
			out.writeObject(info);
		} catch (IOException e) {
			closed = true;
			System.out.println("Il socket del player " + code + " � stato chiuso con errore");
//			e.printStackTrace();
		}
		
	}

	public ObjectOutputStream getSocketOut() {
		return out;
	}

	public void addOnNewGameObject(Consumer<? super ServerGameObject> consumer) {

		onNewGameObject.add(consumer);
		
	}

	public void addOnDeadGameObject(Consumer<? super ServerGameObject> consumer) {

		onDeadGameObject.add(consumer);
		
	}

	@Override
	public HTTPMessage<?> getMessageForClient() {

		return HTTPMessageFactory.updatePlayerMessage(this);
		
	}

	public void kill() {
		
		write(HTTPMessageFactory.getRemoveEnemyMessage(this));
		
	}

	public String getDirection() {
		return this.direction;
	}
	
	public PVector getPos() {
		
		return this.pos;
		
	}

}
