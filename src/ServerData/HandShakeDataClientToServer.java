package ServerData;

import java.io.Serializable;

import Collision.PVector;

public class HandShakeDataClientToServer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3292388186846326137L;
	
	private PVector pos;

	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}
	
}
