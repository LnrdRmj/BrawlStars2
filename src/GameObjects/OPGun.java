package GameObjects;

import Utils.PVector;

public class OPGun extends Gun{

	public OPGun(PVector p) {
		
		super(p);
		
	}
	
	public void shoot(int mouseX, int mouseY) {
		
		int n = 32;
		
		for (int i = 0; i < n; i++) {
			
			new Bullet((int)playerPos.x, (int)playerPos.y, angleDirection + 2 * Math.PI / n * i);
			
		}
		
	}
	
}
