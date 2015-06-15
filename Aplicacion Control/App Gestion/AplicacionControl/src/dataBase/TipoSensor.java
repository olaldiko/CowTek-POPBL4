package dataBase;

  import java.sql.Date;

/**
 * clase para crear tipos de sensores e introducirlas en el servidor de bd
 * @author gorka
 *
 */
  public class TipoSensor{
  	int TipoSensorID;
  	String descripcion;
  	int modeloID;
  	
  	public TipoSensor(String descripcion, int modeloID){
  		this.descripcion = descripcion;
  		this.modeloID = modeloID;
  	}
  	
  	public TipoSensor(int tipoSensorID, String descripcion, int modeloID) {
  		this.TipoSensorID= tipoSensorID;
  		this.descripcion = descripcion;
  		this.modeloID = modeloID;
  	}

	public int introducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ();

  		String sql = "INSERT INTO tipoSensor(Descripcion, ModeloID) " +
  				 "VALUES ('"+descripcion+"', '"+modeloID+"');";

  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  		
  	}

	@Override
	public String toString() {
		return "TipoSensor [TipoSensorID=" + TipoSensorID + ", descripcion="
				+ descripcion + ", modeloID=" + modeloID + "]";
	}
	
	
  }