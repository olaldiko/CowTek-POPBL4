
import java.net.*;
import java.io.*;

public class ServerSock extends Thread{

	final int puerto = 6000;
	ServerSocket sc;
	Socket so;
	String mensajeRecibido;
	DataOutputStream salida;
	DataInputStream entrada;
	BufferedReader entradaBuffered;
	
	public void crearServidor() {
		while(!inicializarServidor());
	}
	
	public boolean inicializarServidor() {
		boolean ok = false;
		
		try {
			sc = new ServerSocket(puerto);
			so = new Socket();
			so = sc.accept();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Creando sockets", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error creando sockets: " + e.getMessage());
			try {
				sc.close();
				so.close();
			} catch (IOException e1) {
				//JOptionPane.showMessageDialog(null, e.getMessage(), "Cerrando sockets", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error cerrando sockets: " + e.getMessage());
				return true;
			}
			return false;
		}
		
		//JOptionPane.showMessageDialog (null, "Un cliente se ha conectado", "Cliente", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Un cliente se ha conectado");
		
		try {
			entrada = new DataInputStream(so.getInputStream());
			salida = new DataOutputStream(so.getOutputStream());
			entradaBuffered = new BufferedReader(new InputStreamReader(so.getInputStream()));
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Creando entradas y salidas", JOptionPane.ERROR);
			System.out.println("Error creando entradas y salidas: " + e.getMessage());
			try {
				entradaBuffered.close();
			} catch (IOException e1) {
				//JOptionPane.showMessageDialog(null, e.getMessage(), "Cerrando entrada", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error cerrando entrada: " + e.getMessage());
				return true;
			}
			return false;
		}
		
		do {
			try {
				System.out.println("Mensaje recibido");
				mensajeRecibido = entradaBuffered.readLine();
				switch(guardarDato(mensajeRecibido)){
				case 3:
					ok = true;
					break;
				case 4:
					ok = false;
					if(!cerrarConexion()) return true;
					break;
				case 0:
					return true;
				}
				
			} catch (IOException e) {
				//JOptionPane.showMessageDialog(null, e.getMessage(), "Recibiendo data", JOptionPane.ERROR);
				System.out.println("Error recibiendo data: " + e.getMessage());
			}
		} while(ok);
		
		return false;
	}
	
	private boolean cerrarConexion() {
		try {
			sc.close();
			so.close();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Cerrando sockets", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error cerrando sockets: " + e.getMessage());
			return false;
		}
		
		try {
			entradaBuffered.close();
		} catch (IOException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Cerrando entrada", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error cerrando entrada: " + e.getMessage());
			return false;
		}
		return true;
	}

	private int guardarDato(String mensajeRecibido) {
		int modo = 0;
		int idPlaca = 0;
		int idSensor = 0;
		float valor_1 = 0;
		float valor_2 = 0;
		
		char caracterInicial, caracterFinal;
		
		caracterInicial = mensajeRecibido.charAt(0);
		caracterFinal = mensajeRecibido.charAt(mensajeRecibido.length() - 1);
		
		if((caracterInicial == '$') && (caracterFinal == '$')) {
			String mensaje = mensajeRecibido.substring(1, mensajeRecibido.length() - 1);
			String[] datos = mensaje.split("%");
			
			modo = Integer.parseInt(datos[0]);
			
			switch (modo) {
			case 1:
				idPlaca = Integer.parseInt(datos[1]);
				idSensor = Integer.parseInt(datos[2]);
				valor_1 = Float.parseFloat(datos[3]);
				System.out.println("---------------");
				System.out.println("Placa: " + idPlaca);
				System.out.println("Sensor: " + idSensor);
				System.out.println(valor_1);
				System.out.println("Valor: " + valor_1);
				System.out.println("---------------");
				System.out.println("");
				break;
			case 2:
				System.out.println(mensaje);
				idPlaca = Integer.parseInt(datos[1]);
				valor_1 = Float.parseFloat(datos[2]);
				valor_2 = Float.parseFloat(datos[3]);
				System.out.println("---------------");
				System.out.println("Placa: " + idPlaca);
				System.out.println("Coordenadas: (" + valor_1 + "; " + valor_2 + ")");
				System.out.println("---------------");
				System.out.println("");
				break;
			case 3:
				System.out.println("Hello packet");
				break;
			case 4:
				System.out.println("Bye bye packet");
				break;
			}
		}
		return modo;
	}
}