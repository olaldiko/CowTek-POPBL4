  package JDBC;

  import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


  public class DatosEstacion{
	  int sensorID;
	  int estacionID;
	  Date fechaHora;
	  float valor;
	  int unidadID;
  	
  	public DatosEstacion(int sensorID, int estacionID, Date fechaHora, float valor, int unidadID){
  		this.sensorID = sensorID;
  		this.estacionID = estacionID;
  		this.fechaHora = fechaHora;
  		this.valor = valor;
  		this.unidadID = unidadID;
  	}
  	
	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO DATOSESTACION (SENSORID, ESTACIONID, FECHAHORA, VALOR, UNIDADID) " +
  				 "VALUES ('"+estacionID+"', '"+estacionID+"', '"+fechaHora+"', '"+valor+"', '"+unidadID+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}

	
	@Override
	public String toString() {
		return "DatosEstacion [sensorID=" + sensorID + ", estacionID="
				+ estacionID + ", fechaHora=" + fechaHora + ", valor=" + valor
				+ ", unidadID=" + unidadID + "]";
	}
  }