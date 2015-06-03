package JDBC;

import java.sql.Date;


public class Vaca{
	int vacaID;
	String nombre;
	String raza;
	Date fechaNacimiento;
	
	public Vaca(String nombre, String raza, Date fechaNacimiento){
		this.nombre = nombre;
		this.raza = raza;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Vaca(int vacaID, String nombre, String raza,
			Date fechaNacimiento) {
		this.vacaID = vacaID;
		this.nombre = nombre;
		this.raza = raza;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int intoducirEnBD(){
		int rowsAffected = 0;
		JDBC dbConnection = new JDBC ("root","");
		
		String sql = "INSERT INTO VACA (Nombre, Raza, FechaNacimiento) " +
				 "VALUES ('"+nombre+"', '"+raza+"', '"+fechaNacimiento+"');";
		rowsAffected=dbConnection.ejecutarUpdate(sql);
		System.out.println("FilasAfectadas: "+rowsAffected);
		
		return rowsAffected;
		
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
