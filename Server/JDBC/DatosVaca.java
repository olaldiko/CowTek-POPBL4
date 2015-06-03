  package JDBC;

  import java.sql.Date;


  public class DatosVaca{
	  int sensorID;
	  int vacaID;
	  Date fechaHora;
	  float valor;	  
	  int unidadID;
  	
  	public DatosVaca(int sensorID, int vacaID, Date fechaHora, float valor, int unidad){
  		this.sensorID = sensorID;
  		this.vacaID = vacaID;
  		this.fechaHora = fechaHora;
  		this.valor = valor;
  		this.unidadID = unidad;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO DATOSVACA(SENSORID, VACAID, FECHAHORA, VALOR, UNIDADID) " +
  				 "VALUES ('"+sensorID+"', '"+vacaID+"', '"+fechaHora+"', '"+valor+"', '"+unidadID+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }