package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jfree.ui.RefineryUtilities;

import analisisDatos.AnalisisDatos;
import analisisDatos.AnalisisHumedad;
import dataBase.JDBC;


/**
 * 
 * Interfaz grafica mediante Java swing encargada de mostrar los datos recopilados
 * y analizados en la explotacion
 * 
 * @author gorka
 *
 */
	
public class InterfazUsuario extends JFrame{

	JTabbedPane pestañas;
	JPanel panelGenerico;
    private JLabel texto;           // etiqueta o texto no editable
    private JTextField caja;        // caja de texto, para insertar datos
    private JButton boton;          // boton con una determinada accion
    
	public InterfazUsuario() {
		super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana

    }
	
	/**
	 * Configurar los rasgos generales de la ventana y añade 
	 */
	private void configurarVentana() {
		pestañas =new JTabbedPane();
        this.setTitle("COWTEK");                  				// colocamos titulo a la ventana
        this.setSize(1024, 720);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        
        
        pestañas.addTab("Datos Generales", completarPanel1());
        pestañas.addTab("Posicionamiento", completarPanel2());
        pestañas.addTab("Datos Individualizados", completarPanel3());
        pestañas.addTab("Datos Estaciones", completarPanel4());
        pestañas.addTab("Introducir/Eliminar vaca", completarPanel5());
        
        this.setContentPane(pestañas);
        
	}
	/**
	 * muestra los datos de las vacas y da la posibilidad de realizar cambios
	 * @return
	 */
 
	
    private Component completarPanel5() {
    	JPanel modificaciones = new Modificaciones();
		return modificaciones;
	}
    
    /**
     * muestra todos los datos de las estaciones
     * @return
     */
    private Component completarPanel4() {
		JPanel estacion = new PanelEstaciones();
		return estacion;
	}
	/**
	 * Muestra todos los datos de las vacas
	 * @return
	 */
	private Component completarPanel3() {
    	JPanel panelIndividualizado = new PanelIndividualizado();
		return panelIndividualizado;
	}
	
	/**
	 * Recibe los datos de posicion de las vacas y los muestra en un mapa
	 * @return
	 */
	private Component completarPanel2() {
    	SwingFXWebView posicion = new SwingFXWebView();
		Double coordenadaY =  42.907140535552294;
		Double coordenadaX = -2.415549470114115;
    	posicion.Geolocalizar(coordenadaY, coordenadaX);
    	return posicion;
	}
	
	/**
	 * primer panel de la aplicacion, dibuja el graficos de la regresion lineal de los datos recopilados
	 * @return
	 */
	private Component completarPanel1() {
    	panelGenerico=new PanelGraficos(); 
		return panelGenerico;
	}

}
