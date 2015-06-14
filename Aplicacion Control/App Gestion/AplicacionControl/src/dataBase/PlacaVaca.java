package dataBase;

  import java.sql.Date;
  /**
   * clase para crear placas de vacas e introducirlas en el servidor de bd
   * @author gorka
   *
   */

  public class PlacaVaca{
	  int vacaID;
	  int placaID;
	  Date fechaInicio;
	  Date fechaFinal;
  	
  	public PlacaVaca(int vacaID, int placaID, Date fecha, Date fechaFinal){
  		this.vacaID = vacaID;
  		this.placaID = placaID;
  		this.fechaInicio = fecha;
  		this.fechaFinal= fechaFinal;
  	}
  	
  	public int introducirEnBD(){
  		int placaVacaID = 0;
  		JDBC dbConnection = new JDBC ();
  		
  		String sql = "INSERT INTO placaVaca (VacaID, PlacaID, FechaInicio, FechaFinal)" +
  				 "VALUES ('"+vacaID+"', '"+placaID+"', '"+fechaInicio+"', NULL);";
  				 		//+ "\""+fechaFinal+"\");";
  		
  		placaVacaID=dbConnection.ejecutarUpdate(sql);
  		
  		return placaVacaID;
  	}

	@Override
	public String toString() {
		return "PlacaVaca [vacaID=" + vacaID + ", placaID=" + placaID
				+ ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
				+ "]";
	}
  	
  }