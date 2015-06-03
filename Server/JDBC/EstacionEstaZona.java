package JDBC;

  import java.sql.Date;


  public class EstacionEstaZona{
	  int estacionID;
	  int zonaID;
	  Date fechaInicio;
	  Date fechaFinal;
	  String descripcion;
  	
  	public EstacionEstaZona(int estacionID, int zonaID, Date fechaInicio, Date fechaFinal, String descripcion){
  		this.estacionID = estacionID;
  		this.zonaID = zonaID;
  		this.fechaInicio = fechaInicio;
  		this.fechaFinal = fechaFinal;
  		this.descripcion = descripcion;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO ESTACIONESTAZONA (ESTACIONID, ZONAID, FECHAINICIO, FECHAFINAL, DESCRIPCION) " +
  				 "VALUES ('"+estacionID+"', '"+zonaID+"', '"+fechaInicio+"', '"+fechaFinal+"',"
  				 		+ " '"+descripcion+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}
  }