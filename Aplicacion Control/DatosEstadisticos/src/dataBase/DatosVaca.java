  package dataBase;

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
  	
  	public int introducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ();
  		
  		String sql = "INSERT INTO datosVaca(SensorID, VacaID, FechaHora, Valor, UnidadID) " +
  				 "VALUES ('"+sensorID+"', '"+vacaID+"', '"+fechaHora+"', '"+valor+"', '"+unidadID+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }