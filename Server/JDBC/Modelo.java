package JDBC;

import java.sql.Date;


public class Modelo{
	int modeloID;
	String nombre;
	String descripcion;
	float precio;
	Date fechaCreacion;
	int fabricanteID;
	
	public Modelo(String nombre, String descripcion, float precio, Date fechaCreacion, int fabricanteID){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
		this.fabricanteID = fabricanteID;
	}
	
	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");

		String sql = "INSERT INTO FABRICANTE(NOMBRE, DESCRIPCION, FECHACREACION) " +
				 "VALUES ('"+nombre+"', '"+descripcion+"', '"+fechaCreacion+"');";
	
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}	
	
}
