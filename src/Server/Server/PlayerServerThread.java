package Server.Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

import Server.HTTPMessage;
import Server.Server.GameObjects.Bullet;
import Server.Server.GameObjects.ServerGameObject;
import ServerData.BulletData;
import Utils.HTTPMessages;

import static Logger.Logger.*;

public class PlayerServerThread implements Runnable {

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
	
	private List<ServerGameObject> toUpdate;
	private List<ServerGameObject> toRemove 	  	= new Vector<>();
	private List<ServerGameObject> toAdd 	 		= new Vector<>();
	
	private List<Consumer<? super ServerGameObject>> onNewGameObject;
	private List<Consumer<? super ServerGameObject>> onDeadGameObject;
	
	public PlayerServerThread(Socket newPlayer) throws IOException {

		this.player = newPlayer;

		code = codeGen;
		codeGen++;

		toUpdate = new Vector<ServerGameObject>();
		toRemove = new Vector<ServerGameObject>();
		toAdd = new Vector<ServerGameObject>();
		onNewGameObject = new Vector<>();
		onDeadGameObject = new Vector<>();
		
//		out = new PrintWriter(this.player.getOutputStream(), true);
//		in = new BufferedReader(new InputStreamReader(this.player.getInputStream()));
		out = new ObjectOutputStream(newPlayer.getOutputStream());
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
					
					ServerGameObject newBullet = new Bullet(d.getPos(), d.getAngleDirection(), out);
					
					toUpdate.add(newBullet);

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
		// TODO Auto-generated method stub
		
		toUpdate.forEach(obj -> {
			
			obj.update();
			
			if (obj.isDead()) {
				
				toRemove.add(obj);
				onDeadGameObject.forEach(el -> el.accept(obj));
				
			}
			
		});
		
		if (toRemove.size() > 0) {
			toUpdate.removeAll(toRemove);
			toRemove.clear();
		}
		
		if (toAdd.size() > 0) {
			toUpdate.addAll(toAdd);
			toAdd.clear();
		}
		
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
			e.printStackTrace();
		}
	}

	public List<ServerGameObject> getToUpdate() {
		return this.toUpdate;
	}

	public void writeAllInfo(ObjectOutputStream out) {
		
		try {
			
			// Informazioni del player stesso
			out.writeObject(new HTTPMessage<String>(HTTPMessages.PLAYER_POS, this.getInfo()));
			
		} catch (IOException e) {
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

}
