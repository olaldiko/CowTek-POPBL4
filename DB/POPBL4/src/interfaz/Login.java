package interfaz;


		import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
		 
		import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
		
		
		public class Login extends JFrame implements ActionListener{
		    static int ADMIN = 1;
		    static int USUARIO = 0;
		    
		    private JLabel label, label2;
		    private JTextField user;
		    private JPasswordField pass;
		    private JButton boton1;
		         
		    int usuario = 0;
		    public void Login() { 
		    	
		    	this.setTitle("VacasSA");                  				// colocamos titulo a la ventana
		        this.setSize(750, 500);                                 // colocamos tamanio a la ventana (ancho, alto)
		        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
		        this.setLayout(new BorderLayout());                    
		        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
		        
		        getContentPane().add(crearCampos(), BorderLayout.CENTER);
		        
		       
		         
		        setVisible(true);
		                 

		    }
		    
		    private Component crearCampos() {
				JPanel panel = new JPanel();
				
				 //CREAR ETIQUETA Y CAMBIAR ATRIBUTOS
		        label=new JLabel("ID DE USUARIO");
		        label.setForeground(Color.red);
		        label.setFont(new Font("Arial",Font.BOLD,16));
		        label.setBounds(20,10,150,30);
		        panel.add(label);
		        //CREAR CAMPO DE TEXTO Y COLOCAR EN POSICION
		        user=new JTextField(15);
		        user.setBounds(150,10,150,30);
		        panel.add(user); 
		         
		        //CREAR ETIQUETA Y CAMBIAR ATRIBUTOS
		        label2=new JLabel("PASSWORD");
		        label2.setForeground(Color.red);
		        label2.setFont(new Font("Arial",Font.BOLD,16));
		        label2.setBounds(20,60,150,30);
		        panel.add(label2);
		        
		        //CREAR CAMPO DE TEXTO Y COLOCAR EN POSICION
		        pass=new JPasswordField(15);
		        pass.setBounds(150,60,150,30);
		        panel.add(pass);     
		         
		        //CREAR BOTON Y CAMBIAR ATRIBUTOS
		        boton1  =  new JButton ("INGRESAR");        
		        boton1.setForeground(Color.BLUE);
		        boton1.setFont(new Font("Arial",Font.BOLD,18));
		        boton1.setBounds(150,90,150,30);        
		        //AQUI SE AGREGA EL EVENTO DEL BOTON DIRECTAMENTE

		        boton1.addActionListener(this);
				boton1.setActionCommand("1");   
		        
             	panel.add(boton1);
             	
				return panel;
			}

			public void actionPerformed(ActionEvent e) {
				String origen = e.getActionCommand();
				switch(origen){
				case "1":	if( user.getText().equals("admin") && pass.getText().equals("123") ){
                    InterfazUsuario interfazUsuario = new InterfazUsuario();
                 	interfazUsuario.setVisible(true);
                 	this.dispose();
                 	                 	
                }else{
                                     
                 
                if( !user.getText().equals("admin") ){
                    JOptionPane.showMessageDialog(null,"USUARIO INCORRECTO");
                    
                }else           
                if( !pass.getText().equals("123") )
                    JOptionPane.showMessageDialog(null,"PASSWORD INCORRECTO");
                	
                }	break;
				}
		    }
		}
