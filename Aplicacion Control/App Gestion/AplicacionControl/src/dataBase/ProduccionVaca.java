package dataBase;

  import java.sql.Date;
/**
 * clase para las producciones de las vacas e introducirlas en el servidor de bd
 * @author gorka
 *
 */

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
  	
  	public int introducirEnBD(){
  		int rowsAffected = 0;
  		JDBC dbConnection = new JDBC ();
  		
  		String sql = "INSERT INTO produccionVaca (VacaID, OrdenaderoID, FechaHora, Cantidad)" +
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