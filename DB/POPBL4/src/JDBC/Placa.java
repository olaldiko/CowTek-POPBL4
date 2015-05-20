package JDBC;

import java.sql.Date;


public class Placa{
	int placaID;
	Date fechaCompra;
	int modeloID ;
	
	public Placa(int placaID, Date fechaCompra, int modeloID){
		this.placaID = placaID;
		this.fechaCompra = fechaCompra;
		this.modeloID = modeloID;
	}
	
	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO VACA " + // (PlacaID, FechaCompra, TipoPlacaID)
				 "VALUES ('"+placaID+"', '"+fechaCompra+"', '"+modeloID+"');";
		System.out.println(sql);
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
	}

	public int getPlacaID() {
		return placaID;
	}

	public void setPlacaID(int placaID) {
		this.placaID = placaID;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getModeloID() {
		return modeloID;
	}

	public void setModeloID(int modeloID) {
		this.modeloID = modeloID;
	}
	

}
