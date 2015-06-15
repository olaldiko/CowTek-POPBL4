package dataBase;

import java.sql.Date;

/**
 * Clase para crear ordeñaderos e introducirlos en la db
 * 
 * @author gorka
 *
 */


public class Ordenadero{
	int ordenaderoID;
	String descripcion;
	int modeloID;
	int estacionID;
	
	public Ordenadero(String descripcion, int modeloID, int estacionID){
		this.descripcion = descripcion;
		this.modeloID = modeloID;
		this.estacionID = estacionID;
	}
	
	public Ordenadero(int ordenaderoID, String descripcion, int modeloID,
			int estacionID) {
		this.ordenaderoID = ordenaderoID;
		this.descripcion = descripcion;
		this.modeloID = modeloID;
		this.estacionID = estacionID;
	}

	@Override
	public String toString() {
		return "Ordenadero [ordenaderoID=" + ordenaderoID + ", descripcion="
				+ descripcion + ", modeloID=" + modeloID + ", estacionID="
				+ estacionID + "]";
	}

	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO ORDENADERO (ORDENADEROID, DESCRIPCION, MODELOID, ESTACIONID) " +
				 "VALUES ('"+ordenaderoID+"', '"+descripcion+"',"
				 		+ "'"+modeloID+"', '"+estacionID+"');";
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
	}
}