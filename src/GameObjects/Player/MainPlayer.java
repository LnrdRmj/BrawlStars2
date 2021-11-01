package GameObjects.Player;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Animation.Animator;
import Collision.PVector;
import GameObjects.Enemy;
import GameObjects.GameObject;
import GameObjects.Bullets.Bullet;
import GameObjects.Guns.Gun;
import Graphic.Canvas;
import Graphic.Frame;
import Graphic.Game;
import Graphic.Renderer;
import Server.HTTPMessage;
import ServerData.BasicData;
import ServerData.BulletData;
import ServerData.HandShakeDataClientToServer;
import ServerData.PlayerData;
import Utils.Friction;
import Utils.HTTPMessages;
import Utils.KeyAction;
import Utils.PVectorUtil;
import Utils.StringUtils;

import static Logger.Logger.*;

public class MainPlayer extends Player implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	private PVector gunPos;
	
	private boolean w = false, a = false, s = false, d = false;
	
	private Gun gun;
	private Vector<String> inputsPressed;
	private Map<String, KeyAction> keyToAction;
	
	private Socket socket;
	private ObjectOutputStream out;
	
	protected ArrayList<Runnable> onUpdate;
	
	public MainPlayer(Canvas canvas){
		
		super();
		
		canvas.addKeyListener(this);

		gunPos = new PVector(pos.x + thick.getWidth() * .3, pos.y + (int)(thick.getHeight()) * .3);
		gun = new Gun(gunPos, canvas);
		canvas.addMouseListener(gun);
		
		inputsPressed = new Vector<String>();
		keyToAction = new HashMap<String, KeyAction>();
		
		populateKeyAction();
		
		onUpdate = new ArrayList<>();

		Renderer.addGameObjectToRender(this);
		
	}
	
	public void draw(Graphics g) {
		
//		hitBox.draw(g);
		animator.drawFrame(g);
		
	}
	
	public void update() {
		
		velocity.add(acc);
		
		if 		(velocity.x >= MAX_VELOCITY) 	velocity.x =  MAX_VELOCITY;
		else if (velocity.x <= -MAX_VELOCITY)	velocity.x = -MAX_VELOCITY;
		if 		(velocity.y >= MAX_VELOCITY) 	velocity.y =  MAX_VELOCITY;
		else if (velocity.y <= -MAX_VELOCITY)	velocity.y = -MAX_VELOCITY;
		
		applyFrictionToVelocity();
		
		if (outOfWindow()) {
			
//			stopPlayer();
//			acc.x += - acc.x * .1;
//			acc.y += - acc.y * .1;
			
		}
		else {
			
			pos.add(velocity);
			gunPos.add(velocity);
//			if (out != null) out.println(pos.x + ";" + pos.y); E' fatto con gli observer
			
		}
		
		onUpdate.forEach(obs -> obs.run());
		
	}
	
	public void addOnUpdateObserver(Runnable onUpdate) {
		
		this.onUpdate.add(onUpdate);
		
	}
	
	// KeyEvent handlers
	@Override
	public void keyPressed(KeyEvent e) {
		
//		System.out.println(e.toString());
//		System.out.println("Hai premuto " + e.getKeyChar());
		
		char keyChar = e.getKeyChar();
		
		switch(e.getKeyChar()) {
		case 'w':
			
			if (!w) {
				acc.add(0, -ACCELERATION);
				w = true;
				
				animator.start(Animator.INDIETRO);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
				direction = "w";
				
			}
			
			break;
		case 'a':
			
			if (!a) {
				acc.add(-ACCELERATION, 0);
				a = true;
				
				animator.start(Animator.SINISTRA);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
				direction = "a";
				
			}
			
			break;
		case 's':
			
			if (!s) {
				acc.add(0, ACCELERATION);
				
				s = true;
				
				animator.start(Animator.AVANTI);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
				direction = "s";
				
			}
			
			break;
		case 'd':
			
			if (!d) {
				acc.add(ACCELERATION, 0);
				d = true;
				
				animator.start(Animator.DESTRA);
				
				inputsPressed.add(StringUtils.charToString(keyChar));
				
				direction = "d";
				
			}
			
			break;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
//		System.out.println("Hai rilasciato " + e.getKeyChar());
		
		char keyChar = e.getKeyChar();
		
		switch(keyChar) {
		case 'w':
			
			acc.add(0, ACCELERATION);
			
			w = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0) {
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				direction = inputsPressed.lastElement();
				animator.start(Animator.WASDtoDirection(direction));
			}
			else {
				animator.stop();
				direction = " ";
			}
			
			break;
		case 'a':
			
			keyToAction.get("a").doSomething();
			
			break;
		case 's':
			
			acc.add(0, -ACCELERATION);
			
			s = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0) {
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				direction = inputsPressed.lastElement();
				animator.start(Animator.WASDtoDirection(direction));
			}
			else {
				animator.stop();
				direction = " ";
			}
			
			break;
		case 'd':
			
			acc.add(-ACCELERATION, 0);
			
			d = false;
			
			inputsPressed.remove(StringUtils.charToString(keyChar));
			
			if (inputsPressed.size() > 0) {
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				direction = inputsPressed.lastElement();
				animator.start(Animator.WASDtoDirection(direction));
			}
			else {
				animator.stop();
				direction = " ";
			}
			
			break;
			
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void applyFrictionToVelocity() {
		
		this.velocity.add(Friction.calculateFriction(this.velocity));
		
	}
	
	private boolean outOfWindow() {
		
		PVector p = PVectorUtil.addVectors(pos, velocity);
				
		return p.x < 0 || p.x > Game.config.width - thick.width || p.y < 0 || p.y > Game.config.height - thick.height;
		
	}
	
	private void populateKeyAction() {
		
		keyToAction.put("a", new AAction());
		
	}
	
	public Gun getGun() {
		return gun;
	}
	
	// Questo va messo nel server
	@Override
	public void hit(GameObject hit) {
		
		
		
	}
	
	private class AAction implements KeyAction{

		private char key = 'a';
		
		@Override
		public void doSomething() {
			
			acc.add(ACCELERATION, 0);
			
			a = false;
			
			inputsPressed.remove(StringUtils.charToString(key));
			
			if (inputsPressed.size() > 0) {
				// Se io smetto di premere un tasto ma in precedenza ne avevo già premuto un altro,
				// allora devo riprendere l'animazione del pulsante precedente
				direction = inputsPressed.lastElement();
				animator.start(Animator.WASDtoDirection(direction));
			}
			else {
				animator.stop();
				direction = " ";
			}
			
		}
		
	}

	public void setOutStream(ObjectOutputStream out) {
		
		this.out = out;
		
		// Serve per scrivere al server ogni qualvolta che il player viene aggiornato
		onUpdate.add( () -> {
			
			try {
				
				out.writeObject(new HTTPMessage<>(HTTPMessages.PLAYER_DATA, new PlayerData(this)));
				
			} catch (IOException e) {
				System.out.println(e.getMessage());	
				e.printStackTrace();
			}
			
		});
		
		gun.addOnShoot((bullet) -> {
			
			try {
				
				out.writeObject(new HTTPMessage<>(HTTPMessages.BULLET_SHOT, new BulletData(bullet)));
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		});
			
		
	}

	@Override
	public void applyData(BasicData data) {

		
		
	}

	
}
