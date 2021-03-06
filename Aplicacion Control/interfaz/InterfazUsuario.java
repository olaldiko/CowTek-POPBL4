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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import JDBC.JDBC;
import JDBC.Staments;

public class InterfazUsuario extends JFrame implements ActionListener{
	
	JTabbedPane pesta�as;
	JPanel panelGenerico;
    private JLabel texto;           // etiqueta o texto no editable
    private JTextField caja;        // caja de texto, para insertar datos
    private JButton boton;          // boton con una determinada accion
    
	public InterfazUsuario() {
		super();                    // usamos el contructor de la clase padre JFrame
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes

    }
	private void configurarVentana() {
		pesta�as =new JTabbedPane();
        this.setTitle("VacasSA");                  				// colocamos titulo a la ventana
        this.setSize(1024, 720);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        
        
        pesta�as.addTab("Panel 1", completarPanel1());
        pesta�as.addTab("Panel 2", completarPanel2());
        
        this.setContentPane(pesta�as);
        
	}
 
    private Component completarPanel2() {
    	GeoLocalizacion posicion = new GeoLocalizacion();
		Double coordenadaY = 42.1162766;
		Double coordenadaX = -2.3710892405;
    	return posicion.Geolocalizar(coordenadaY, coordenadaX);
	}
	private Component completarPanel1() {
    	panelGenerico=new JPanel(new GridLayout(2,2));
    	panelGenerico.add(Grafico1());
    	panelGenerico.add(Grafico2());
    	panelGenerico.add(Grafico3());
    	panelGenerico.add(Grafico4());    	
		return panelGenerico;
	}
	private Component Grafico4() {List<Double> scores = new ArrayList<>();
    Random random = new Random();
    int maxDataPoints = 40;
    int maxScore = 10;
    for (int i = 0; i < maxDataPoints; i++) {
        scores.add((double) random.nextDouble() * maxScore);
//        scores.add((double) i);
    }
	GraphPanel grafico4 = new GraphPanel(scores);
	return grafico4;
	}
	private Component Grafico3() {
		List<Double> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 40;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((double) random.nextDouble() * maxScore);
//            scores.add((double) i);
        }
		GraphPanel grafico3 = new GraphPanel(scores);
		return grafico3;
	}
	private Component Grafico2() {
		List<Double> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 40;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((double) random.nextDouble() * maxScore);
//            scores.add((double) i);
        }
		GraphPanel grafico2 = new GraphPanel(scores);
		return grafico2;
	}
	private Component Grafico1() {
    	 List<Double> Temperatures = new ArrayList<>();
    	 
 		JDBC dbConnection = new JDBC ("root","");
 		
 		try {
			Temperatures = dbConnection.readResultTemperatures();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		GraphPanel grafico1 = new GraphPanel(Temperatures);
 		
		grafico1.add(new JLabel("Temperaturas"), BorderLayout.NORTH);
		return grafico1;
	}
	private void inicializarComponentes() {
        // creamos los componentes
        texto = new JLabel();
        caja = new JTextField();
        boton = new JButton();
        // configuramos los componentes
        texto.setText("Inserte Nombre");    // colocamos un texto a la etiqueta
        texto.setBounds(50, 50, 100, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        caja.setBounds(150, 50, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        boton.setText("Mostrar Mensaje");   // colocamos un texto al boton
        boton.setBounds(50, 100, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        // adicionamos los componentes a la ventana
    }
 
    public void actionPerformed(ActionEvent e) {
        String nombre = caja.getText();                                 // obtenemos el contenido de la caja de texto
        JOptionPane.showMessageDialog(this, "Hola " + nombre + ".");    // mostramos un mensaje (frame, mensaje)
    }

}
