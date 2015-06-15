package interfaz;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

import dataBase.JDBC;
		
		/**
		 * se encarga de conectarse a la base de datos y asegurarse de que el usuario y contraseña
		 * utilizados ya existen en la BD.		 * 
		 * @author gorka
		 *
		 */
		public class Login extends JFrame implements ActionListener{
		    static int ADMIN = 1;
		    static int USUARIO = 0;
		    
		    private JLabel label, label2;
		    private JTextField user;
		    private JPasswordField pass;
		    private JButton boton1;
		    
		    int usuario = 0;
		    public void Login() { 
		    	this.setTitle("COWTEK");                  				// colocamos titulo a la ventana
		        this.setSize(750, 500);                                 // colocamos tamanio a la ventana (ancho, alto)
		        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla                
		        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
		        
		    	getContentPane().add(crearCampos());
		    	
		    	
		    	setDefaultLookAndFeelDecorated(true);
		        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.CremeSkin"); 
		    	SubstanceLookAndFeel.setCurrentWatermark( new SubstanceImageWatermark("C:/Users/gorka/Downloads/cowtekLogo.png"));
		    	SubstanceLookAndFeel.setImageWatermarkOpacity(new Float(0.10));
		         
		        setVisible(true);
		                 

		    }
		    
		    /**
		     * diseño de la ventana de registro
		     * @return
		     */
		    private Component crearCampos() {
				JPanel panel = new JPanel();
				
				 //CREAR ETIQUETA Y CAMBIAR ATRIBUTOS
		        label=new JLabel("ID DE USUARIO", SwingConstants.CENTER);
		        label.setForeground(Color.BLACK);
		        label.setFont(new Font("Arial",Font.BOLD,16));
		        label.setBounds(300,100,150,30);
		        panel.add(label);
		        
		        //CREAR CAMPO DE TEXTO Y COLOCAR EN POSICION
		        user=new JTextField(15);
		        user.setBounds(300, 130,150,25);
		        panel.add(user);
		         
		        //CREAR ETIQUETA Y CAMBIAR ATRIBUTOS
		        label2=new JLabel("PASSWORD", SwingConstants.CENTER);
		        label2.setForeground(Color.BLACK);
		        label2.setFont(new Font("Arial",Font.BOLD,16));
		        label2.setBounds(300, 160, 150, 30);
		        panel.add(label2);
		        
		        //CREAR CAMPO DE TEXTO Y COLOCAR EN POSICION
		        pass=new JPasswordField(15);
		        pass.setBounds(300, 190, 150, 25);
		        panel.add(pass);
		         
		        //CREAR BOTON Y CAMBIAR ATRIBUTOS
		        boton1  =  new JButton ("INGRESAR");        
		        boton1.setForeground(Color.BLUE);
		        boton1.setFont(new Font("Arial",Font.BOLD,18));
		        boton1.setBounds(300, 300, 150, 30);
		        //AQUI SE AGREGA EL EVENTO DEL BOTON DIRECTAMENTE

		        boton1.addActionListener(this);
				boton1.setActionCommand("1");   
		        
             	panel.add(boton1);
             	panel.setLayout(null);
             	
				return panel;
			}
		    
		    /**
		     * implementacion de action listener y conexion al servidor de BD
		     */
			public void actionPerformed(ActionEvent e) {
				String origen = e.getActionCommand();
				switch(origen){
				case "1":	
					JDBC dbconnection = new JDBC();
					String usuario = (String) user.getText();
					String contraseña = pass.getText();
					try{
					dbconnection.getConnection(usuario, contraseña);
					InterfazUsuario interfazUsuario = new InterfazUsuario();
                 	interfazUsuario.setVisible(true);
                 	this.dispose();
					}catch (SQLException e2) {
	                    JOptionPane.showMessageDialog(null,"USUARIO O CONTRASEÑA INCORRECTO");
					}
                 	               
                    break;
				}
		    }
		}
