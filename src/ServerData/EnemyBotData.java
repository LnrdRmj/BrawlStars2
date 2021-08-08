package ServerData;

import java.io.Serializable;
import Collision.PVector;

public class EnemyBotData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8683419032279229488L;
	
	private PVector pos;
	private Double  angle;
	
	public EnemyBotData(PVector pos, Double angle) {
		super();
		this.pos = pos;
		this.angle = angle;
	}

	public PVector getPos() {
		return pos;
	}

	public Double getAngle() {
		return angle;
	}
	
}
