package JDBC;

import java.sql.Date;


public class Estacion{
	int estacionID;
	String nombre;
	String descripcion;
	
	public Estacion(String nombre, String descripcion){
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Estacion(int estacionID, String nombre, String descripcion) {
		this.estacionID = estacionID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO ESTACION (NOMBRE, DESCRIPCION) " +
				 "VALUES ('"+nombre+"','"+descripcion+"');";
		
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
	}

	@Override
	public String toString() {
		return "Estacion [estacionID=" + estacionID + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}

}