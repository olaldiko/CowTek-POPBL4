package dataBase;

import java.sql.Date;

/**
 * clase para crear tipos de placas e introducirlas en el servidor de bd
 * @author gorka
 *
 */
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


	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO tipoPlaca (Descripcion, ModeloID) " +
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