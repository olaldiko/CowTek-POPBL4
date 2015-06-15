package interfaz;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Clase encargada de graficar un funcion polinomica a partir los coeficientes
 * @author gorka
 *
 */

public class Graficador extends JPanel{
	String nombre, leyendaX, leyendaY, tipoFuncion;
	double coeficienteGrado2;
	double coeficienteGrado1;
	double coeficienteGrado0;
	double coeficiente;
	double exponente;
	double valorMaximo, valorMinimo;
	/**
	 * plotea grafica de tipo polinomico
	 * @param nombre nombre
	 * @param valorMinimo valor minimo
	 * @param valorMaximo valor maximo
	 * @param leyendaX leyenda del eje X
	 * @param leyendaY leyenda del ejeY
	 * @param coeficienteGrado2 coeficiente de grado 2
	 * @param coeficienteGrado1 coeficiente de grado 1
	 * @param coeficienteGrado0 coeficiente de grado 0
	 * @return 
	 */
    public Graficador(String nombre,double valorMinimo, double valorMaximo, 
    		String leyendaX, String leyendaY, double coeficienteGrado2,
    		double coeficienteGrado1, double coeficienteGrado0){
    	this.nombre = nombre;
    	this.valorMaximo = valorMaximo;
    	this.valorMinimo = valorMinimo;
    	this.leyendaY = leyendaY;
    	this.leyendaX = leyendaX;
        this.coeficienteGrado0 = coeficienteGrado0;
        this.coeficienteGrado1 = coeficienteGrado1;
        this.coeficienteGrado2 = coeficienteGrado2;
    	this.tipoFuncion = "polinomica";
    	
    	XYDataset paresDeDatos = generarDatos();
        JFreeChart diagrama = crearDiagrama(paresDeDatos);
        ChartPanel chartPanel = new ChartPanel(diagrama);
        chartPanel.setPreferredSize(new Dimension(400,300));
        this.add(chartPanel);
    }
 /**
  * plotea grafica de tipo exponencial
  * @param nombre nombre
  * @param valorMinimo valor minimo
  * @param valorMaximo valor maximo
  * @param leyendaX leyenda del eje X
  * @param leyendaY leyenda deleje Y
  * @param coeficiente coeficiente
  * @param exponente exponente
  */
    public Graficador(String nombre, double valorMinimo, 
    		double valorMaximo, String leyendaX, String leyendaY,
    		double coeficiente, double exponente) {
    	this.nombre = nombre;
    	this.valorMaximo = valorMaximo;
    	this.valorMinimo = valorMinimo;
    	this.leyendaY = leyendaY;
    	this.leyendaX = leyendaX;
        this.coeficiente = coeficiente;
        this.exponente = exponente;
    	this.tipoFuncion = "exponencial";
    	
    	XYDataset paresDeDatos = generarDatos();
        JFreeChart diagrama = crearDiagrama(paresDeDatos);
        ChartPanel chartPanel = new ChartPanel(diagrama);
        chartPanel.setPreferredSize(new Dimension(400,300));
        this.add(chartPanel);
	}

	private XYDataset generarDatos(){
        //le pasamos una funcion generadora f(x)
        XYSeries datos = new XYSeries("Linea Funcion");
        for(double x=valorMinimo; x<=valorMaximo; x+=0.5) datos.add(x,f(x));
        
        XYSeriesCollection conjuntoDatos = new XYSeriesCollection();
        conjuntoDatos.addSeries(datos);
 
        return conjuntoDatos;
    }
 
    private JFreeChart crearDiagrama(XYDataset conjuntoDatos){
        JFreeChart diag = ChartFactory.createXYLineChart(
                                nombre, //Titulo Grafica
                                leyendaX, // Leyenda eje X
                                leyendaY, // Leyenda eje Y
                                conjuntoDatos, // Los datos
                                PlotOrientation.VERTICAL, //orientacion
                                false, // ver titulo de linea
                                false, //tooltips
                                false  //URL
                            );
        return diag;
    }
 
    //aqui definimos la funcion que desees, en esta caso la f(x) = 4sen(x)
    private double f(double x){
    	switch(tipoFuncion){
    	case "polinomica":
    		return (coeficienteGrado2*Math.pow(x,2)+coeficienteGrado1*Math.pow(x, 1));
    	case "exponencial":
    		return Math.pow(coeficiente*x, exponente);
    	default :
    		return 0; 
    	}
    }
   }