package JDBC;

import java.sql.Date;


public class Principal {

	public static void main(String[] args) {
		Principal demo = new Principal();
		demo.iniciarPrograma();

	}

	private void iniciarPrograma() {
		Date fecha = new Date(11, 5, 2010);
		
		Vaca vaca = new Vaca("Felisa", "Holstein", fecha);
		
		vaca.intoducirEnBD();
	}

}
