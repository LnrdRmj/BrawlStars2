package Server;

import java.io.Serializable;

public class Config implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4256868979727857556L;
	
	public int width = 800;
	public int height = 400;
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [width=" + width + ", height=" + height + "]";
	}
	
	
	
}
