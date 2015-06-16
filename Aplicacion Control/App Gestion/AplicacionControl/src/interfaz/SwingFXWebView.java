package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.javafx.application.PlatformImpl;

import dataBase.DatosVaca;
import dataBase.JDBC;
  
/** 
 * Panel para poder mostrar una pagina web dentro de la aplicacion
 * SwingFXWebView 
 */  

public class SwingFXWebView extends JPanel {  
     
	private Double  coordenadaX;
	private Double coordenadaY;
	private Stage stage;  
    private WebView browser;  
    private JFXPanel jfxPanel;  
    private JButton swingButton;  
    private WebEngine webEngine;  
  
    public SwingFXWebView(){  
        initComponents();  
    }  
  
     
    private void initComponents(){  
         
    	
        jfxPanel = new JFXPanel();  
        createScene();  
         
        setLayout(new BorderLayout());  
        add(jfxPanel, BorderLayout.CENTER);  
         
        swingButton = new JButton();  
        swingButton.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
 
                    @Override
                    public void run() {
                        webEngine.reload();
                    }
                });
            }
        });  
        swingButton.setText("Reload");  
         
        add(swingButton, BorderLayout.SOUTH);  
    }     
     
    /** 
     * createScene 
     * 
     * Note: Key is that Scene needs to be created and run on "FX user thread" 
     *       NOT on the AWT-EventQueue Thread 
     * 
     */  
    private void createScene() {  
        PlatformImpl.startup(new Runnable() {  
            @Override
            public void run() {  
                 
                stage = new Stage();  
                 
                stage.setTitle("Hello Java FX");  
   
                Group root = new Group();  
                Scene scene = new Scene(root); 
                stage.setScene(scene);  
                 
                // Set up the embedded browser:
                browser = new WebView();
                webEngine = browser.getEngine();
                
                JDBC dbConnection = new JDBC();
                List<DatosVaca> ubicacion=dbConnection.getDatosVacas();
                float latitud = 0;
                float longitud = 0;
                
                String url = "http://cowtek.mooo.com/gmap.htm?";
                for (int i=0;i<ubicacion.size(); i++){
                	if (ubicacion.get(i).getUnidadID()==4){
                		latitud = ubicacion.get(i).getValor();
                		longitud = ubicacion.get(i+1).getValor();
                    	url = url+"q="+ubicacion.get(i).getVacaID()+"@"+latitud ;
                    	url = url + ","+longitud+"&";
                	}
                }
                
               // webEngine.load("http://cowtek.mooo.com/gmap.htm?q=Felisa%20veintiuno@"+coordenadaY+","+coordenadaX+"&q=My%20Place@53.5,-2.5");
                System.out.println(url);
                webEngine.load(url);
                ObservableList<Node> children = root.getChildren();
                children.add(browser);                     
                 
                jfxPanel.setScene(scene);  
            }  
        });  
    }  
    
    public void Geolocalizar (Double coordenadaY,Double coordenadaX) {
         this.coordenadaX = coordenadaX;
         this.coordenadaY = coordenadaY;
        
        
    }
}
