package dataBase;

import java.sql.Date;
/**
 * clase para crear sensores e introducirlas en el servidor de bd
 * @author gorka
 *
 */

public class Sensor{
	int sensorID;
	String descripcion;
	int placaID;
	int tipoSensorID;
	  
	public Sensor(String descripcion, int placaID,int tipoSensorID){

		this.descripcion = descripcion;
		this.placaID = placaID;
		this.tipoSensorID = tipoSensorID;
	}
	
	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO sensor (Descripcion, "
				+ "PlacaID, TipoSensorID) " +
				 "Values ('"+descripcion+"','"+placaID+"','"+tipoSensorID+"');";
	
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}
	
}