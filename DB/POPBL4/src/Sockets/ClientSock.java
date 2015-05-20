package Sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSock extends Thread{

	DataInputStream entrada;
	DataOutputStream salida;
	final String HOST = "localhost";
	final int PUERTO=5000;
	Socket sc;
	Scanner teclado = new Scanner (System.in);
	
	//Cliente
	public void initClient() /*ejecuta este metodo para correr el cliente */
	{
		  
	try{
		
	sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/
	
	//creamos el flujo de datos por el que se enviara un mensaje
	 entrada = new DataInputStream(sc.getInputStream());
     salida = new DataOutputStream(sc.getOutputStream());
     	
	//enviamos el mensaje
	salida.writeUTF("hola que tal!!");
	System.out.println("prueba1");
	System.out.println(entrada.readUTF());

	System.out.println("prueba2");
	String respuesta=teclado.next();
	salida.writeUTF(respuesta+"\n");
	
	
	System.out.println("1");
	System.out.println(entrada.readUTF());
	System.out.println("2");
	System.out.println(entrada.readUTF());
	//cerramos la conexión
	System.out.println("cerrada la conexion cliente");
	sc.close();
	}catch(Exception e ){
	System.out.println("Error: "+e.getMessage());
	}
	}
}
