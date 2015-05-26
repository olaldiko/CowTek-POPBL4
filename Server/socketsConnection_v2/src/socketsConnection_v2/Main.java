package socketsConnection_v2;

public class Main {

	public static void main(String[] args) {
		MultiThreadedServer server = new MultiThreadedServer(8080);
		new Thread(server).start();
		
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (true);
		
		//System.out.println("Stopping server");
		//server.stop();
	}

}
