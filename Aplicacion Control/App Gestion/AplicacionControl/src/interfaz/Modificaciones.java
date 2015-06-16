package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dataBase.JDBC;
import dataBase.PlacaVaca;
import dataBase.Vaca;
 
/**
 * clase encargada de introducir, borrar,y modificar datos de vacas en la BD y 
 * el diseño grafico de la interfaz
 * @author gorka
 *
 */
public class Modificaciones extends JPanel implements ActionListener {
 
	JTable jTabla;
	JScrollPane panelS;
	JDateChooser fechaNacimiento_c;
    JFrame frame;
    JPanel panelce,panelde,panelar,panelex;
	Component panelab;
    JLabel nombre_l, raza_l, fechaNacimiento_l, placaID_l ;
    JTextField nombre_f, raza_f, fechaNacimiento_f, placaID_f ;
    JButton boton1,boton2, boton3;
    TrazadorTablaVacas trazador;     
    ModeloColumnasTablaVacas columnas;
    ModeloTablaVacas tabla;
    /**
     * Diseño grafico de la interfaz
     */
    public Modificaciones(){
        panelce  = crearPanelArribaCentroIzda();
        panelde  = crearPanelArribaDcha();
        panelde.setAlignmentX(CENTER_ALIGNMENT);
        panelab  = crearPanelAbajo();
        panelar  = new JPanel();
        panelex = new JPanel();
            
        this.setVisible(true);
        
        nombre_l.setText("Nombre");
        raza_l.setText("Raza");
        fechaNacimiento_l.setText("Fecha de nacimiento");
        placaID_l.setText("PlacaID");
        
        panelar.setLayout(new BorderLayout());
        panelar.add(panelce, BorderLayout.CENTER);
        panelar.add(panelde, BorderLayout.EAST);
         
        panelex.setLayout(new BorderLayout());
        panelex.add(panelar, BorderLayout.NORTH);
        panelex.add(panelab, BorderLayout.SOUTH);
        
        this.add(panelex);
        
    }
    /**
     * Panel con tabla que posee toda la informacion
     * @return
     */
	private Component crearPanelAbajo() {
		JPanel panel = new JPanel();
		panelS = new JScrollPane();
		
		trazador = new TrazadorTablaVacas();
		columnas = new ModeloColumnasTablaVacas(trazador);
		tabla = new ModeloTablaVacas(columnas);
		
		jTabla = new JTable(tabla,columnas);
		jTabla.setFillsViewportHeight(true);
		panelS.setViewportView(jTabla);
		panel.add(panelS,BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * Diseño de botones
	 * @return
	 */
	private JPanel crearPanelArribaDcha() {
		JPanel panel = new JPanel(new GridLayout(3,0));

        boton1 = new JButton("Insertar");
        boton1.addActionListener(this);
        boton1.setActionCommand("insertar");
        boton2 = new JButton("Borrar");
        boton2.addActionListener(this);
        boton2.setActionCommand("borrar");
        
       //  panel.setPreferredSize(new Dimension(200,100));
        boton1.setPreferredSize(new Dimension(100, 30));
        boton2.setPreferredSize(new Dimension(100, 30));
        
        
        panel.add(boton1);
        panel.add(boton2);
        
        
		return panel;
	}

	/**
	 * Diseño de formulario para introducir la informacion
	 * @return
	 */
	private JPanel crearPanelArribaCentroIzda() {
		panelce = new JPanel();
		panelce.setSize(500, 350);
		
		nombre_l = new JLabel();
        raza_l = new JLabel();
        fechaNacimiento_l = new JLabel();
        placaID_l = new JLabel();
        
        nombre_f = new JTextField(15);        
        nombre_f.setBounds(150,10,150,30);
        raza_f = new JTextField(15);
        raza_f.setBounds(150,10,150,30);
        fechaNacimiento_c = new JDateChooser();
        fechaNacimiento_c = new JDateChooser();
        fechaNacimiento_c.setBounds(150,10,150,30);
        placaID_f= new JTextField(15);
        placaID_f.setBounds(150,10,150,30);
		
        JPanel paneliz = new JPanel();
		paneliz.setLayout(new GridLayout(4,0));
	    paneliz.add(nombre_l);
	    paneliz.add(raza_l);
	    paneliz.add(fechaNacimiento_l);
	    paneliz.add(placaID_l);

        JPanel panelde = new JPanel();
	    panelde.setLayout(new GridLayout(4,0));
	    panelde.add(nombre_f);
	    panelde.add(raza_f);
	    panelde.add(fechaNacimiento_c);
	    panelde.add(placaID_f);
        
        panelce.add(paneliz, BorderLayout.WEST);
        panelce.add(panelde, BorderLayout.EAST);
        
		return panelce;
	}
	/**
	 * implementacion de los botones y su funcion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		
		case "borrar":
			if (jTabla.getSelectedRow() != -1){
				int vacaID = -1;
				vacaID = (int)(jTabla.getValueAt(jTabla.getSelectedRow(), 0));
				JDBC dbConnection = new JDBC();
				System.out.println(vacaID);
				dbConnection.borrarVaca(vacaID);
				tabla.leerTablaDB();
				tabla.fireTableDataChanged();	
			}
			break;
			
		case "insertar" :
			String nombre = nombre_f.getText();
			String raza = raza_f.getText();
			java.util.Date fechaUtil =  fechaNacimiento_c.getDate();
		    java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());
			int placaID = Integer.parseInt(placaID_f.getText());
						
			Vaca vaca = new Vaca(nombre, raza , fecha);
			int vacaID = vaca.introducirEnBD();
			
			
			if (vacaID!=-1){
				PlacaVaca vacaTienePlaca = new PlacaVaca(vacaID, placaID, fecha, null);
				vacaTienePlaca.introducirEnBD();
				tabla.leerTablaDB();
				tabla.fireTableDataChanged();	
				System.out.println("se ha introducido correctamente");
			}
			break;
		}
	}
 
}