package JDBC;

import java.sql.Date;


public class TipoPlaca{
	int tipoPlacaID ;
	String descripcion ;
	int modeloID ;
	  
	public TipoPlaca(String Descripcion, int modeloID){
		this.descripcion = Descripcion;
		this.modeloID = modeloID;
	}
	
	public TipoPlaca(int tipoPlacaID, String descripcion, int modeloID) {
		this.tipoPlacaID = tipoPlacaID;
		this.descripcion = descripcion;
		this.modeloID = modeloID;
	}


	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO TIPOPLACA (DESCRIPCION, MODELOID) " +
				 "VALUES ('"+descripcion+"', '"+modeloID+"');";
	
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}
	
	@Override
	public String toString() {
		return "TipoPlaca [tipoPlacaID=" + tipoPlacaID + ", descripcion="
				+ descripcion + ", modeloID=" + modeloID + "]";
	}
	
}
