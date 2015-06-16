package main;

import interfaz.Login;

/**
 * aplicacion para inicializar la aplicacion
 * @author gorka
 *
 */
public class Main {

	
	public void gestion(){
		Login log = new Login();
		log.Login();

		/*InterfazUsuario log = new InterfazUsuario();
		log.setVisible(true);
		*/
	}

	public static void main(String[] args) {
		Main aplication = new Main();
		aplication.gestion();
	}
}