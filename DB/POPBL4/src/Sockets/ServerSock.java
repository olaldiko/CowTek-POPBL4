package Sockets;

import java.net.*;
import java.io.*;

public class ServerSock extends Thread{

	final int PUERTO=5000;
	ServerSocket sc;
	Socket so;
	String mensajeRecibido;
	DataOutputStream salida;
	DataInputStream entrada;
	
	//SERVIDOR
	
  public void CrearServidor(){
	  
	  try{
	  sc = new ServerSocket(PUERTO );/* crea socket servidor que escuchara en puerto 5000*/
	  so=new Socket();
	  System.out.println("Esperando una conexión:");
	  so = sc.accept();
	  
	  //Inicia el socket, ahora esta esperando una conexión por parte del cliente
	  System.out.println("Un cliente se ha conectado.");
	  
	  //Canales de entrada y salida de datos
	  entrada = new DataInputStream(so.getInputStream());
	  salida = new DataOutputStream(so.getOutputStream());
	  BufferedReader entradaBuffered = new BufferedReader(new InputStreamReader(so.getInputStream()));
	  
	  System.out.println("Confirmando conexion al cliente....");
	  int i=0;
	  while(true){
		i++;
		System.out.println(i); 
		mensajeRecibido = entradaBuffered.readLine();
	  	System.out.println(mensajeRecibido);
	  	sleep(1000);
	  	
	  }
	  
	 /*salida.writeUTF("Conexión exitosa...n envia un mensaje :D");

	  
	  //Recepcion de mensaje
	  //mensajeRecibido = entradaBuffered.readLine();
	  System.out.println(mensajeRecibido);
	  salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
	  salida.writeUTF("Gracias por conectarte, adios!");
	  System.out.println("Cerrando conexión servidor...");
	  sc.close();//Aqui se cierra la conexión con el cliente
	  */
	  }catch(Exception e )
	  {
	  System.out.println("Error: "+e.getMessage());
	  }
  }
}