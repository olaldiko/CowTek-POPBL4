package JDBC;

import java.sql.Date;


public class Ordeņadero{
	int ordenaderoID;
	String descripcion;
	int modeloID;
	int estacionID;
	
	public Ordeņadero(String descripcion, int modeloID, int estacionID){
		this.descripcion = descripcion;
		this.modeloID = modeloID;
		this.estacionID = estacionID;
	}
	
	public Ordeņadero(int ordenaderoID, String descripcion, int modeloID,
			int estacionID) {
		this.ordenaderoID = ordenaderoID;
		this.descripcion = descripcion;
		this.modeloID = modeloID;
		this.estacionID = estacionID;
	}

	@Override
	public String toString() {
		return "Ordeņadero [ordenaderoID=" + ordenaderoID + ", descripcion="
				+ descripcion + ", modeloID=" + modeloID + ", estacionID="
				+ estacionID + "]";
	}

	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO ORDENADERO (ORDENADEROID, DESCRIPCION, MODELOID, ESTACIONID) " +
				 "VALUES ('"+ordenaderoID+"', '"+descripcion+"',"
				 		+ "'"+modeloID+"', '"+estacionID+"');";
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
	}
}
	