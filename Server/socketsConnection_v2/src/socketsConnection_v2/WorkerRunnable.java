package socketsConnection_v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

	protected Socket clientSocket = null;
	protected String serverText = null;
	
	
	public WorkerRunnable(Socket clientSocket, String serverText) {
		super();
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	@Override
	public void run() {
		try {
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			long time = System.currentTimeMillis();
			/*
			output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + 
					this.serverText + " - " + time).getBytes());
			*/
			
			BufferedReader entradaBuffered = new BufferedReader(new InputStreamReader(output.getInputStream()));
			output.close();
			input.close();
			System.out.println("Request processed: " + time);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
