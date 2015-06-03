package JDBC;

  import java.sql.Date;


  public class ProduccionVaca{
	  int vacaID;
	  int ordenaderoID;
	  Date fechaHora;
	  float cantidad;
  	
  	public ProduccionVaca(int vacaID, int ordenaderoID, Date fechaHora, float cantidad){
  		this.vacaID = vacaID;
  		this.ordenaderoID = ordenaderoID;
  		this.fechaHora = fechaHora;
  		this.cantidad = cantidad;
  	}
  	
  	public int intoducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ("root","");
  		
  		String sql = "INSERT INTO PRODUCIONVACA (VACAID, ORDENADEROID, FECHAHORA, CANTIDAD)" +
  				 "VALUES ('"+vacaID+"', '"+ordenaderoID+"', '"+fechaHora+"', '"+cantidad+"');";
  		
  		System.out.println(sql);
  		rowsAffected=dbConnection.ejecutarUpdate(sql);
  		System.out.println("FilasAfectadas: "+rowsAffected);
  		
  		return rowsAffected;
  	}

	@Override
	public String toString() {
		return "ProduccionVaca [vacaID=" + vacaID + ", ordenaderoID="
				+ ordenaderoID + ", fechaHora=" + fechaHora + ", cantidad="
				+ cantidad + "]";
	}
  	
  }