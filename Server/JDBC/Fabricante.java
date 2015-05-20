package JDBC;

import java.sql.Date;


public class Fabricante{
	int fabricanteID;
	String nombre;
	String descripcion;
	Date fechaCreacion;
	
	public Fabricante(String nombre, String descripcion, Date fechaCreacion){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
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
