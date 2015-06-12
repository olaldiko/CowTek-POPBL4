package interfaz;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;

import maps.java.ShowMaps;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class GeoLocalizacion extends JPanel{

    public Component Geolocalizar (Double coordenadaY,Double coordenadaX) {
        final Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
               
        browser.loadURL("https://www.google.es/maps/@"+coordenadaY+","+coordenadaX+",11z");
        //Falta señalizar de alguna manera la posicion exacta de las vacas mediante algun simbolo
        return browserView;
        
    }
}
