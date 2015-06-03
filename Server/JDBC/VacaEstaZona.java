  package JDBC;

  import java.sql.Date;


  public class VacaEstaZona{
	  int vacaID;
	  int zonaID;
	  Date fechaHora;
  	
  	public VacaEstaZona(int vacaID, int zonaID, Date fechaHora){
  		this.vacaID = vacaID;
  		this.zonaID = zonaID;
  		this.fechaHora = fechaHora;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO VACAESTAZONA (VACAID, ZONAID, FECHAHORA) " +
  				 "VALUES ('"+vacaID+"', '"+zonaID+"', '"+fechaHora+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }