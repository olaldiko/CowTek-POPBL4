package JDBC;

  import java.sql.Date;


  public class ConsumoVaca{
	  @Override
	public String toString() {
		return "ConsumoVaca [vacaID=" + vacaID + ", comederoID=" + comederoID
				+ ", fechaHora=" + fechaHora + ", cantidad=" + cantidad + "]";
	}

	int vacaID;
	  int comederoID;
	  Date fechaHora;
	  float cantidad;
  	
  	public ConsumoVaca(int vacaID, int comederoID, Date fechaHora, float cantidad){
  		this.vacaID = vacaID;
  		this.comederoID = comederoID;
  		this.fechaHora = fechaHora;
  		this.cantidad = cantidad;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO CONSUMOVACA (VACAID, COMEDEROID, FECHAHORA, CANTIDAD) " +
  				 "VALUES ('"+vacaID+"', '"+comederoID+"', '"+fechaHora+"', '"+cantidad+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }