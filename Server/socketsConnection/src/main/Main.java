package main;

public class Main {

	public static void main(String[] args) {
		ServerSock servidor = new ServerSock();
		Runnable task1 = () -> {
			servidor.crearServidor();
		};
		
		new Thread(task1).start();
	}

}
