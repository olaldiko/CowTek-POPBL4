package JDBC;

  import java.sql.Date;


  public class PlacaEstacion{

	int estacionID;
	  int placaID;
	  Date fechaInicio;
	  Date fechaFinal;
  	
  	public PlacaEstacion(int estacionID, int placaID, Date fechaInicio, Date fechaFinal){
  		this.estacionID = estacionID;
  		this.placaID = placaID;
  		this.fechaInicio = fechaInicio;
  		this.fechaFinal= fechaFinal;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO PLACAESTACION (ESTACIONID, PLACAID, FECHAINICIO, FECHAFINAL)" +
  				 "VALUES ('"+estacionID+"', '"+placaID+"', '"+fechaInicio+"', '"+fechaFinal+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  	
  	 @Override
 	public String toString() {
 		return "PlacaEstacion [estacionID=" + estacionID + ", placaID="
 				+ placaID + ", fechaInicio=" + fechaInicio + ", fechaFinal="
 				+ fechaFinal + "]";
 	}
  }