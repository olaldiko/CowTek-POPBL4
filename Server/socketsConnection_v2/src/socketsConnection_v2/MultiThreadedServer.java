package socketsConnection_v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

	protected int serverPort = 8080;
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;
	
	public MultiThreadedServer (int port) {
		this.serverPort = port;
	}
	
	public void run () {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		
		while (!isStopped) {
			Socket clientSocket = null;
			
			try {
				clientSocket = this.serverSocket.accept();
				System.out.println("Client accepted");
			} catch (IOException e) {
				if ( isStopped()) {
					System.out.println("Server stopped");
					return;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			new Thread(new WorkerRunnable(clientSocket, "Multithreaded server")).start();
		}
		System.out.println("Server stopped");
	}
	
	private synchronized boolean isStopped () {
		return this.isStopped;
	}
	
	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
			System.out.println("Server stopped");
		} catch (IOException e) {
			throw new RuntimeException("Error closing server", e);
		}
	}
	
	private void openServerSocket () {
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
			System.out.println("Opened port " + this.serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + this.serverPort, e);
		}
	}
	
}
