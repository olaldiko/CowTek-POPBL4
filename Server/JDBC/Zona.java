  package JDBC;

  import java.sql.Date;


  public class Zona{
	  int zonaID;
	  String descripcion;
	  float latitud;
	  float longitud;
	  
  	
  	public Zona(String descripcion, float latitud, float longitud){
  		this.descripcion = descripcion;
  		this.latitud = latitud;
  		this.longitud = longitud;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO ZONA (DESCRIPCION, LATITUD, LONGITUD) " +
  				 "VALUES ('"+descripcion+"', '"+latitud+"', '"+longitud+"');";
  		
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }
  