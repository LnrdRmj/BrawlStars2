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

import Server.HTTPMessage;
import Server.Server.GameObjects.Bullet;
import Server.Server.GameObjects.ServerGameObject;
import ServerData.BulletData;
import Utils.HTTPMessages;

import static Logger.Logger.*;

/*
 * Serve soltanto per la gestione della connessione col Client
 * 
 * */

public class PlayerServerThread extends ServerGameObject implements Runnable {

	private Socket player;

	public static int codeGen = 1;
	private int code;

//	private PrintWriter out;
//	private BufferedReader in;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private Integer x;
	private Integer y;

	private Thread thisThread;
	private boolean closed = false;
	
//	private List<ServerGameObject> toUpdate;
//	private List<ServerGameObject> toRemove 	  	= new Vector<>();
//	private List<ServerGameObject> toAdd 	 		= new Vector<>();
	
	private List<Consumer<? super ServerGameObject>> onNewGameObject;
	private List<Consumer<? super ServerGameObject>> onDeadGameObject;
	
	public PlayerServerThread(Socket newPlayer) throws IOException {

		super(new ObjectOutputStream(newPlayer.getOutputStream()));
		
		this.player = newPlayer;

		code = codeGen;
		codeGen++;

		onNewGameObject = new Vector<>();
		onDeadGameObject = new Vector<>();
		
//		out = new PrintWriter(this.player.getOutputStream(), true);
//		in = new BufferedReader(new InputStreamReader(this.player.getInputStream()));
		out = client;
		in = new ObjectInputStream(newPlayer.getInputStream());

		thisThread = new Thread(this);
		thisThread.start();

	}

	@Override
	public void run() {

//		System.out.println("Server - Ho appena scritto al client");
//		out.println("hey sono i server piacere di conoscerti");

		while (true) {

			try {

				Object cmd = in.readObject();
				
//				logServer(cmd.toString());
				
				if (!(cmd instanceof HTTPMessage)) continue;
				
				// TODO: questa cosa mi torna poco ma boh
				HTTPMessage<?> comand = (HTTPMessage<?>) cmd;

//				logServer("Ho ricevuto un messagio col seguente comando" + comand.getComand());
//				logServer(comand.getComand());
				
				switch(comand.getComand()) {
				
				case HTTPMessages.PLAYER_POS:
					
//					System.out.println("Server - ho ricevuto un messaggio dal client");
//					System.out.println("Server - " + comand);
					 
					// pos (x,y)
					String[] pos = ((String)comand.getMessageBody()).split(";");
					x = (int) Double.parseDouble(pos[0]);
					y = (int) Double.parseDouble(pos[1]);
					
					break;
					
				case HTTPMessages.BULLET_SHOT:
					
					if (!(comand.getMessageBody() instanceof BulletData)) break;
					
					BulletData d = (BulletData) comand.getMessageBody();
					
					StringTokenizer st = new StringTokenizer(d.getPos(), ";");
					
					ServerGameObject newBullet = new Bullet(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), d.getAngleDirection(), out);
					
					onNewGameObject.forEach(el -> el.accept(newBullet));
					
					break;
					
				}
				
			} catch (SocketException | EOFException e) {
				close();
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				break;
			}

		}

	}

	public void update() {
		
		
		
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

	public String getInfo() {

		return x + ";" + y + ";" + code;

	}

	public boolean isClosed() {
		return closed;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void write(HTTPMessage<?> info) {
		
		try {
			out.writeObject(info);
		} catch (IOException e) {
			closed = true;
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return new HTTPMessage<>(HTTPMessages.PLAYER_POS, this.getInfo());
	}

	public void kill() {
		
		write(new HTTPMessage<>(HTTPMessages.REMOVE_ENEMY, this.code));
		
	}

}
