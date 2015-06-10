package dataBase;

  import java.sql.Date;


  public class PlacaVaca{
	  int vacaID;
	  int placaID;
	  Date fechaInicio;
	  Date fechaFinal;
  	
  	public PlacaVaca(int vacaID, int placaID, Date fechaInicio, Date fechaFinal){
  		this.vacaID = vacaID;
  		this.placaID = placaID;
  		this.fechaInicio = fechaInicio;
  		this.fechaFinal= fechaFinal;
  	}
  	
  	public int introducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ();
  		
  		String sql = "INSERT INTO placaVaca (VacaID, PlacaID, FechaInicio, FechaFinal)" +
  				 "VALUES ('"+vacaID+"', '"+placaID+"', '"+fechaInicio+"', '"+fechaFinal+"');";
  		
  		//System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		//System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}

	@Override
	public String toString() {
		return "PlacaVaca [vacaID=" + vacaID + ", placaID=" + placaID
				+ ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
				+ "]";
	}
  	
  	
  }