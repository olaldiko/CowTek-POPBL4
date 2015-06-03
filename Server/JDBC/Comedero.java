//Falta auto_increment en comederoID

package JDBC;

import java.sql.Date;


public class Comedero{
	int comederoID=4;
	String descripcion;
	int modeloID;
	int estacionID;
	  
	public Comedero(String descripcion, int modeloID, int estacionID){
		this.descripcion = descripcion;
		this.modeloID = modeloID;
		this.estacionID = estacionID;
	}
	
	public Comedero(int comederoID, String descripcion, int modeloID, int estacionID) {
		this.comederoID = comederoID;
		this.descripcion = descripcion;
		this.modeloID = modeloID;
		this.estacionID = estacionID;
	}

	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO COMEDERO (COMEDEROID, DESCRIPCION, MODELOID, ESTACIONID) " +
				 "VALUES ('"+comederoID+"','"+descripcion+"','"+modeloID+"','"+estacionID+"');";
		
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
	}

	@Override
	public String toString() {
		return "Comedero [comederoID=" + comederoID + ", descripcion="
				+ descripcion + ", modeloID=" + modeloID + ", estacionID="
				+ estacionID + "]";
	}

}