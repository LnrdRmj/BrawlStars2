package Logger;

public class Logger {

	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(String prefix, String delim, Object message) {
		log(prefix + delim + message);
	}
	
	public static void log(String prefix, Object message) {
		log(prefix + " - " + message.toString());
	}
	
	public static void logClient(Object message) {
		log("Client", message);
	}
	
	public static void logServer(Object message) {
		log("Server", message);
	}
	
}
