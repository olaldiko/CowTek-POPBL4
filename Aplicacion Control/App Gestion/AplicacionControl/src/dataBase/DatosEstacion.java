package dataBase;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * clase para crear DatosEstacion e introducirlos en la db
 * @author gorka
 *
 */
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
  	
	public int introducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ();
  		
  		String sql = "INSERT INTO datosEstacion (SensorID, EstacionID, FechaHora, Valor, UnidadID) " +
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

	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}

	public int getEstacionID() {
		return estacionID;
	}

	public void setEstacionID(int estacionID) {
		this.estacionID = estacionID;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getUnidadID() {
		return unidadID;
	}

	public void setUnidadID(int unidadID) {
		this.unidadID = unidadID;
	}
	
  }