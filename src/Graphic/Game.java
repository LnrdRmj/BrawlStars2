package Graphic;


import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JPanel;

import Collision.PVector;
import GameObjects.GameObject;
import GameObjects.Bullets.Bullet;
import GameObjects.Bullets.NormalBullet;
import GameObjects.Player.EnemyPlayer;
import GameObjects.Player.MainPlayer;
import Server.Config;
import Server.RetryConnection;
import Server.Client.ServerListener;
import Server.HTTPMessage.HTTPEvent;
import Server.HTTPMessage.HTTPMessage;
import Server.HTTPMessage.HTTPMessageFactory;
import ServerData.BulletData;
import ServerData.HandShakeDataClientToServer;
import ServerData.HandShakeDataServerToClient;
import ServerData.PlayerData;
import Utils.HTTPMessages;
import Utils.PVectorUtil;
import messages.BrokerReceivedMessage;
import messages.MessageSender;
import messages.Subscriber;

import static Logger.Logger.*;

public class Game implements Runnable, KeyListener, HTTPEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread mainThread;
	private MainPlayer player;
	private Canvas canvas;
	private Frame frame;
	private Socket server;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Map<Integer, EnemyPlayer> enemies;
	private Map<Integer, GameObject> gameObjects;
	
	private Subscriber removeEnemySub;
	private Subscriber drawBulletSub;
	private Subscriber createdNewEnemySub;
	private Subscriber enemyUpdateSub;
	
	public static Config config;

	public Game() {
		startNewGame();
	}
	
	private void startNewGame() {
		
		canvas = new Canvas();
		canvas.addKeyListener(this);

		frame = new Frame();
		frame.getContentPane().add(this.getCanvas());
		
		config = new Config();
		
		player = new MainPlayer(canvas);
		
		try {
			
			setServer(new Socket("localhost", 7777));
			
		} catch (IOException e) {
			
			// Prova ogni 5 secondi a riconnetterti al server
			new RetryConnection("localhost", 7777, (server) -> {
				
				setServer(server);
				
			}, 5000);
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		initializeSubs();
		
		BrokerReceivedMessage.getInstance().registerSubscribe(HTTPMessages.REMOVE_ENEMY, removeEnemySub);
		BrokerReceivedMessage.getInstance().registerSubscribe(HTTPMessages.DRAW_BULLET, drawBulletSub);
		BrokerReceivedMessage.getInstance().registerSubscribe(HTTPMessages.NEW_ENEMY, createdNewEnemySub);
		BrokerReceivedMessage.getInstance().registerSubscribe(HTTPMessages.PLAYER_DATA, enemyUpdateSub);
		
	}
	
	public void setServer(Socket server) {
		
		this.server = server;
		createInOutStreams(server);
		
		MessageSender.init(out);
		
		handShake();
		player.setOutStream(out);
		new ServerListener(in, this);
			
	}
	
	public void createInOutStreams(Socket server){
		
		try {
			in = new ObjectInputStream(server.getInputStream());
			out = new ObjectOutputStream(server.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {

		while (true) {
			
			canvas.repaint();

			// 60 Frames BABYYYY
			wait(16);
			
		}

	}
	
	public void handShake() {
		
		// Una volta che ho ricevuto il messaggio di handshake da parte del server mando quello del client
		// cioè il mio
		HandShakeDataClientToServer handShake = new HandShakeDataClientToServer();
		handShake.setPlayerData(new PlayerData(player));
		
		try {
			
			MessageSender.getInstance().sendMessage(HTTPMessageFactory.getHandhakeMessage(handShake));
			
			HTTPMessage<?> handShakeMessage = (HTTPMessage<?>)in.readObject();

			if (!(handShakeMessage.getMessageBody() instanceof HandShakeDataServerToClient)) return;
			
			HandShakeDataServerToClient c = (HandShakeDataServerToClient) handShakeMessage.getMessageBody();
			
			Game.config = c.getConfig();
			
			// Setto le dimensioni del JPanel uguali a quella del server
			frame.setBounds(new Rectangle(Game.config.width, Game.config.height));
			canvas.setFocusable(true);
			canvas.requestFocusInWindow();
			
			// Codice univoco generato dal server
			player.setCode(c.getCode());
			
			logClient(c);
			
			startGame();
			
			Toast.setText(this.player.getCode());
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void startGame() {
		
		enemies = new HashMap<>();
		gameObjects = new HashMap<>();
		
		mainThread = new Thread(this);
		mainThread.start();
		
	}
	
	private void wait(int milliseconds) {
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public static void addGameObject(GameObject toAdd) {

		Renderer.addGameObjectToRender(toAdd);

	}
	
	public static void removeGameObject(GameObject toRemove) {
		
		Renderer.removeGameObjectToRender(toRemove);
		
	}

	public JPanel getCanvas() {
		return canvas;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyChar()) {
		case '': // R: Non so perchï¿½ ma dovrebbe essere una R maiuscola
			
			if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) {
				
				System.out.println("New game");
//				startNewGame();
				
			}
			
			break;

		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	// Chiamato da ServerListener
	@Override
	public void onMessageReceived(HTTPMessage<?> message) {
		
		//System.out.println("Client - ho ricevuto qualcosa dal server: " + message);
		
		switch(message.getComand()) {
		
		case HTTPMessages.PLAYER_DATA: 
			
			if (!(message.getMessageBody() instanceof PlayerData)) break;
			
			PlayerData enemyData = ((PlayerData)message.getMessageBody()); 
			
			// Se si tratta di un nemico
			if (! ( player.getCode().equals( enemyData.getCode() ) ) ){
				
				EnemyPlayer enemy = enemies.get(enemyData.getCode());
				
				if ( enemy == null ) {
					enemies.put(enemyData.getCode(), new EnemyPlayer(enemyData));
				}
				else {
					
					enemy.applyData(enemyData);
					
				}
			
			}
			
			break;
			
		case HTTPMessages.DRAW_BULLET:
			
			if (!(message.getMessageBody() instanceof BulletData)) break;
			
			BulletData bulletData = (BulletData) message.getMessageBody();
			
			if (bulletData.isDead()) {
				
				GameObject toDelete = gameObjects.get(bulletData.getCode());

				if (toDelete != null) {
					toDelete.kill();
					Renderer.removeGameObjectToRender(toDelete);
					gameObjects.remove(bulletData.getCode());
				}
				
			}
			else {
				
				Bullet bullet = (Bullet) gameObjects.get(bulletData.getCode());
				
				if (bullet == null) {
					
					bullet = new NormalBullet(bulletData);
					gameObjects.put(bulletData.getCode(), bullet);
					Renderer.addGameObjectToRender(bullet);
					
				}
				else {
					bullet.applyData(bulletData);
				}
				
			}
			
			break;
		
		case HTTPMessages.REMOVE_ENEMY:

			if (!(message.getMessageBody() instanceof Integer)) break;
			
			Integer code = (Integer) message.getMessageBody();
			
			Renderer.removeGameObjectToRender(enemies.get(code));
			enemies.remove(code);
			
			break;
			
		}
		
	}

	private void initializeSubs() {
		
		// -----------------------------------------
		// -----------------------------------------
		removeEnemySub = (tipo, messaggio) -> {
			
			if ( !(messaggio.getMessageBody() instanceof Integer) ) return;
			
			Integer code = (Integer) messaggio.getMessageBody();
			
			Renderer.removeGameObjectToRender(enemies.get(code));
			enemies.remove(code);
			
		};
		
		// -----------------------------------------
		// -----------------------------------------
		drawBulletSub = (tipo, messaggio) -> {
			
			if ( !( messaggio.getMessageBody() instanceof BulletData ) ) return;
			
			BulletData bulletData = (BulletData) messaggio.getMessageBody();
			
			if (bulletData.isDead()) {
				
				GameObject toDelete = gameObjects.get(bulletData.getCode());

				if (toDelete != null) {
					toDelete.kill();
					Renderer.removeGameObjectToRender(toDelete);
					gameObjects.remove(bulletData.getCode());
				}
				
			}
			else {
				
				Bullet bullet = (Bullet) gameObjects.get(bulletData.getCode());
				
				if (bullet == null) {
					
					bullet = new NormalBullet(bulletData);
					gameObjects.put(bulletData.getCode(), bullet);
					Renderer.addGameObjectToRender(bullet);
					
				}
				else {
					bullet.applyData(bulletData);
				}
				
			}
		};
		
		// -----------------------------------------
		// -----------------------------------------
		createdNewEnemySub = (tipo, messaggio) -> {
			
			// Creato un nuovo nemico dal server, gestiscilo
			
			System.out.println("Un nuovo nemico si avvicina");
			System.out.println(messaggio.getMessageBody().toString());
			
			PlayerData enemyData = (PlayerData) messaggio.getMessageBody();
			
			enemies.put(enemyData.getCode(), new EnemyPlayer(enemyData));
			
		};
		
		// -----------------------------------------
		// -----------------------------------------
		enemyUpdateSub = (tipo, messaggio) -> {
			
			PlayerData enemyData = (PlayerData) messaggio.getMessageBody();
			
			if (! ( player.getCode().equals( enemyData.getCode() ) ) ){
			
				EnemyPlayer enemy = enemies.get(enemyData.getCode());
				
				if ( enemy == null ) {
					enemies.put(enemyData.getCode(), new EnemyPlayer(enemyData));
				}
				else {
					enemy.applyData(enemyData);
				}
			
			}
			
		};
		
	}
	
}
