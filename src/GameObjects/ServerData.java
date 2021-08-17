package GameObjects;

import java.io.Serializable;

import Collision.HitBox;

public class ServerData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5705047180549861844L;
	
	protected HitBox hitBox;
	protected String name = "";
	
	public ServerData() {
		
	}
	
	public ServerData (HitBox hitBox) {
		this.hitBox = hitBox;
	}
	
	public ServerData (HitBox hitBox, String name) {
		this.hitBox = hitBox;
		this.name = name;
	}
	
	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}
	
	public HitBox getHitBox() {
		return this.hitBox;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		
		hitBox.setBounds(x, y, width, height);
		
	}
	
}
