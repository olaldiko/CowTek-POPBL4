package dataBase;

  import java.sql.Date;

  /**
   * clase para crear consumoVaca e introducirlos en la db
   * @author gorka
   *
   */
  
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
  	
  	public int introducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ();
  		
  		String sql = "INSERT INTO consumoVaca (VacaID, ComederoID, FechaHora, Cantidad) " +
  				 "VALUES ('"+vacaID+"', '"+comederoID+"', '"+fechaHora+"', '"+cantidad+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }