package interfaz;
/**
 * aplicacion para inicializar la aplicacion
 * @author gorka
 *
 */
public class Principal {

	
	public void gestion(){
		Login log = new Login();
		log.Login();

		/*InterfazUsuario log = new InterfazUsuario();
		log.setVisible(true);
		*/
	}

	public static void main(String[] args) {
		Principal aplication = new Principal();
		aplication.gestion();
	}
}


