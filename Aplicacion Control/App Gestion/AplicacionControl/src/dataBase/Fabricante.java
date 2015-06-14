package dataBase;

import java.sql.Date;
/**
 * clase para crear fabricantes e introducirlos en la db
 * @author gorka
 *
 */

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
	
	public Fabricante(int fabricanteID, String nombre, String descripcion,
			Date fechaCreacion) {
		this.fabricanteID = fabricanteID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
	}


	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();

		String sql = "INSERT INTO fabricante(Nombre, Descripcion, FechaCreacion) " +
				 "VALUES ('"+nombre+"', '"+descripcion+"', NOW());";
	
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}	
	@Override
	public String toString() {
		return "Fabricante [fabricanteID=" + fabricanteID + ", nombre="
				+ nombre + ", descripcion=" + descripcion + ", fechaCreacion="
				+ fechaCreacion + "]";
	}
	
}