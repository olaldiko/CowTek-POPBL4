package JDBC;

import java.sql.Date;

public class Unidad {

	int unidadID;
	String nombre;
	
	public Unidad(String nombre){
		this.nombre=nombre;
	}
	public Unidad(int unidadID, String nombre){
		this.unidadID = unidadID;
		this.nombre=nombre;
	}
	
	
	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO UNIDAD(NOMBRE) " +
				 "VALUES ('"+nombre+"');";
		
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
	}

	@Override
	public String toString() {
		return "Unidad [unidadID=" + unidadID + ", nombre=" + nombre + "]";
	}
}
