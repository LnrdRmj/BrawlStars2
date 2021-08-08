package Utils;

public class Observer{

	private Runnable doSomething; 
	
	
	
	public void update() {
		
		doSomething.run();
		
	}
	
}
