package interfaz;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import analisisDatos.AnalisisDatos;
import dataBase.DatosVistaVaca;
import dataBase.JDBC;

/**
 * dibuja los graficos de regresion lineal con los datos de la BD y los plotea
 * @author gorka
 *
 */
public class PanelGraficos extends JPanel{
	public PanelGraficos(){
	this.setLayout(new GridLayout(2,2));
	this.add(Grafico1());        
	this.add(Grafico2());
	this.add(Grafico3());
	this.add(Grafico4());    	
	}
	/**
	 * muestra un historico de las temperaturas de los ultimos 3 dias
	 * @return
	 */

	private Component Grafico4() {List<Double> scores = new ArrayList<>();
    JDBC dbconnection = new JDBC();
    List<DatosVistaVaca> vacas = dbconnection.getVistaTempAmb();
    if ((vacas.size()-144)<0){

        for (int i = 0; i < vacas.size(); i++) {
            scores.add((double) vacas.get(i).getValor());
        }
    }else{
    	for (int i = (vacas.size()-144); i < vacas.size(); i++) {  //se recogen 144 datos cada 3 dias
        scores.add((double) vacas.get(i).getValor());
        }
    }
	GraphPanel grafico4 = new GraphPanel(scores);
	grafico4.setBorder(BorderFactory.createTitledBorder("Historico temperaturas"));
	
	return grafico4;
	}
	 /**
	  * Realiza un grafico con la regresion lineal de la produccion en funcion de la temperatura
	  * @return
	  */
	private Component Grafico3() {

 		AnalisisDatos analisis = new AnalisisDatos();
 		analisis.analizarTodo();
 		String temperatura = analisis.getaTemp().getEcuacion();
		
		int i= 0;
		double grado0=0, grado1=0, grado2=0 ;
		StringTokenizer st = new StringTokenizer(temperatura,"+*x",true);
       while (st.hasMoreTokens()) {  
       	if (i==0){
           	grado0 = Double.parseDouble(st.nextToken());
           	System.out.println(grado0);
       	}
       	if (i==1){
           	grado1 = Double.parseDouble(st.nextToken());
           	System.out.println(grado1);
       	}
       	if (i==5){
           	grado2
           	= Double.parseDouble(st.nextToken());
           	System.out.println(grado2);
       	}
       	i++;
       	st.nextToken();
       }
       Component miGraficador = new Graficador("Temperatura", -20.0, 55.0, "Temperatra ºC","Producción", grado2, grado1, grado0);
       JPanel panel = new JPanel();
       panel.add(miGraficador);
       panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		return panel;
	}
	/**
	 * Realiza un grafico con la regresion lineal de la produccion en funcion de la ingesta
	 * @return
	 */
	private Component Grafico2() {

        Component miGraficador = new Graficador("Ingesta", 0.0, 100.0, "kg", "Producción", 5, 2, 1);
        JPanel panel = new JPanel();
        panel.add(miGraficador);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		return panel;
		
	}

    /**
     * Realiza un grafico con la regresion lineal de la produccion en funcion de la humedad
     */
	private Component Grafico1() {
    	
 		AnalisisDatos analisis = new AnalisisDatos();
 		analisis.analizarTodo();
 		String humedad = analisis.getaHum().getEcuacion();
 		
 		int i= 0;
 		double grado0=0, grado1=0, grado2=0 ;
 		StringTokenizer st = new StringTokenizer(humedad,"+*x",true);
        while (st.hasMoreTokens()) {  
        	if (i==0){
            	grado0 = Double.parseDouble(st.nextToken());
            	System.out.println(grado0);
        	}
        	if (i==1){
            	grado1 = Double.parseDouble(st.nextToken());
            	System.out.println(grado1);
        	}
        	if (i==5){
            	grado2
            	= Double.parseDouble(st.nextToken());
            	System.out.println(grado2);
        	}
        	i++;
        	st.nextToken();
        }
        
        Component miGraficador = new Graficador("Humedad",0.0, 100.0,"Humedad relativa","Producción", grado2, grado1, grado0);
        JPanel panel = new JPanel();
        panel.add(miGraficador);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
 		
		return panel;
	}

}
