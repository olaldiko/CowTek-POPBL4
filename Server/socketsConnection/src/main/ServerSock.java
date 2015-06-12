package main;

import java.net.*;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.io.*;

import dataBase.DatosEstacion;
import dataBase.DatosVaca;
import dataBase.JDBC;
import dataBase.PlacaEstacion;
import dataBase.PlacaVaca;

public class ServerSock extends Thread{

	final int puerto = 6000;
	ServerSocket sc;
	Socket so;
	String mensajeRecibido;
	DataInputStream entrada;
	BufferedReader entradaBuffered;
	
	public void crearServidor() {
		while(!inicializarServidor());
	}
	
	public boolean inicializarServidor() {
		boolean ok = false;
		long inicio = 0;
		
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
		inicio = System.currentTimeMillis();
		
		try {
			entrada = new DataInputStream(so.getInputStream());
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
					inicio = System.currentTimeMillis();
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
			
			if (System.currentTimeMillis() - inicio > 1000*10) {
				System.out.println("Tiempo de espera agotado");
				if (!cerrarConexion()) return false;
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
				
				if(getVacaID(idPlaca) != -1) {
					DatosVaca datosVaca = new DatosVaca(idSensor, getVacaID(idPlaca), getCurrentDateTime(), valor_1, 1);
					datosVaca.introducirEnBD();
				}
				if(getEstacionID(idPlaca) != -1) {
					DatosEstacion datosEstacion = new DatosEstacion(idSensor, getEstacionID(idPlaca), getCurrentDateTime(), valor_1, 1);
					datosEstacion.introducirEnBD();
				}
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
				
				if(getVacaID(idPlaca) != -1) {
					DatosVaca datosVaca_1 = new DatosVaca(idSensor, getVacaID(idPlaca), getCurrentDateTime(), valor_1, 10);
					datosVaca_1.introducirEnBD();
					DatosVaca datosVaca_2 = new DatosVaca(idSensor, getVacaID(idPlaca), getCurrentDateTime(), valor_2, 10);
					datosVaca_2.introducirEnBD();
				}
				
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

	private int getVacaID(int idPlaca) {
		JDBC dbConnection = new JDBC ();
		List<PlacaVaca> placasVacas = dbConnection.getPlacasVacas();
		Iterator<PlacaVaca> itrPlacasVacas = placasVacas.iterator();
		while (itrPlacasVacas.hasNext()){
			PlacaVaca placaVaca = itrPlacasVacas.next();
			if(placaVaca.getPlacaID() == idPlaca) return placaVaca.getVacaID();
		}
		return -1;
	}
	
	private int getEstacionID(int idPlaca) {
		JDBC dbConnection = new JDBC ();
		List<PlacaEstacion> placasEstaciones = dbConnection.getPlacasEstaciones();
		Iterator<PlacaEstacion> itrPlacasEstaciones = placasEstaciones.iterator();
		while (itrPlacasEstaciones.hasNext()){
			PlacaEstacion placaEstacion = itrPlacasEstaciones.next();
			if(placaEstacion.getPlacaID() == idPlaca) return placaEstacion.getEstacionID();
		}
		return -1;
	}
	
	public Date getCurrentDateTime(){
		Date date = new Date(System.currentTimeMillis());
		return date;
	}
	
}