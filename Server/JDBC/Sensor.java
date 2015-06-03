package JDBC;

import java.sql.Date;


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
	
	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO SENSOR (DESCRIPCION, "
				+ "PLACAID, TIPOSENSORID) " +
				 "VALUES ('"+descripcion+"','"+placaID+"','"+tipoSensorID+"');";
	
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}
	
}
