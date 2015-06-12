package dataBase;

import java.sql.Date;


public class Placa{
	int placaID;
	Date fechaCompra;
	int tipoPlacaID;
	
	public Placa(Date fechaCompra, int tipoPlacaID){
		this.fechaCompra = fechaCompra;
		this.tipoPlacaID = tipoPlacaID;
	}
	
	public Placa(int placaID, Date fechaCompra, int tipoPlacaID) {
		this.placaID = placaID;
		this.fechaCompra = fechaCompra;
		this.tipoPlacaID = tipoPlacaID;
	}
	public int introducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO placa (FechaCompra, TipoPlacaID)"+
				 "VALUES ('"+fechaCompra+"', '"+tipoPlacaID+"');";

		
		System.out.println(sql);
		
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}	

	@Override
	public String toString() {
		return "Placa [placaID=" + placaID + ", fechaCompra=" + fechaCompra
				+ ", tipoPlacaID=" + tipoPlacaID + "]";
	}

}