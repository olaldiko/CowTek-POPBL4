package interfaz;
/*
 /*
 * Copyright (c) 2000-2015 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */

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

/**
 * This sample demonstrates how to load a web page with Google Maps
 * and control it using JxBrowser API.
 */
public class GeoLocalizacion extends JPanel{


    /**
     * In map.html file default zoom value is set to 4.
     */

    public Component Geolocalizar (Double coordenadaY,Double coordenadaX) {
        final Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);
               
        browser.loadURL("https://www.google.es/maps/@"+coordenadaY+","+coordenadaX+",11z");
        return browserView;
        
    }
}