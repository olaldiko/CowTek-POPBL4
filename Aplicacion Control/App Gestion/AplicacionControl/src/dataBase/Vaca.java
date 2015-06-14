package dataBase;

import java.sql.Date;

/**
 * clase para crear vacas e introducirlas en el servidor de bd
 * @author gorka
 *
 */
public class Vaca{
	int vacaID;
	String nombre;
	String raza;
	Date fechaNacimiento;
	
	public Vaca(String nombre, String raza, Date fecha){
		this.nombre = nombre;
		this.raza = raza;
		this.fechaNacimiento = fecha;
	}
	
	public Vaca(int vacaID, String nombre, String raza,
			Date fechaNacimiento) {
		this.vacaID = vacaID;
		this.nombre = nombre;
		this.raza = raza;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int introducirEnBD(){
		int vacaID = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO vaca (VacaID, Nombre, Raza, FechaNacimiento) " +
				 "VALUES (null, '"+nombre+"', '"+raza+"', '"+fechaNacimiento+"');";
		
		vacaID=dbConnection.ejecutarUpdate(sql);
  		
		return vacaID;
		
	}
	public int borrarEnBD(){
		int vacaID = 0;
		JDBC dbConnection = new JDBC ();
		
		String sql = "INSERT INTO vaca (VacaID, Nombre, Raza, FechaNacimiento) " +
				 "VALUES (null, '"+nombre+"', '"+raza+"', '"+fechaNacimiento+"');";
		
		vacaID=dbConnection.ejecutarUpdate(sql);
  		
		return vacaID;
		
	}
	

	public int getVacaID() {
		return vacaID;
	}
	public void setVacaID(int vacaID) {
		this.vacaID = vacaID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	@Override
	public String toString() {
		return "Vaca [vacaID=" + vacaID + ", nombre=" + nombre + ", raza="
				+ raza + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}