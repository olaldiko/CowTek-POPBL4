  package JDBC;

  import java.sql.Date;


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

	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");

  		String sql = "INSERT INTO TIPOSENSOR(DESCRIPCION, MODELOID) " +
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