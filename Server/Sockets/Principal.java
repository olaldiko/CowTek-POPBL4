package Sockets;

public class Principal {

	public static void main(String[] args) {
		ServerSock servidor = new ServerSock();
		Runnable task1 = () -> {
			servidor.CrearServidor();
		};
		
		ClientSock cliente = new ClientSock();
		Runnable task2 = () -> {
			cliente.initClient();
		};
		new Thread(task1).start();
		//new Thread(task2).start();
		
		;

	}

}
