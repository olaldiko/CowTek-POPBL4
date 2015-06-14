package dataBase;

import java.sql.Date;
/**
 * clase para crear unidades de medida e introducirlas en el servidor de bd
 * @author gorka
 *
 */
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
	
	
	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO unidad(Nombre) " +
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