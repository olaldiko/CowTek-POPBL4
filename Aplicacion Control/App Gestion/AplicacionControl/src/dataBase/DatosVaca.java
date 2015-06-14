 package dataBase;

  import java.sql.Date;

  /**
   * clase para crear DatosVaca e introducirlos en la db
   * @author gorka
   *
   */
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

	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}

	public int getVacaID() {
		return vacaID;
	}

	public void setVacaID(int vacaID) {
		this.vacaID = vacaID;
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