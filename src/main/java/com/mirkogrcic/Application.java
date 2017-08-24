package com.mirkogrcic;

import com.mirkogrcic.gui.ConfigWindow;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Logger;

public class Application {
    private Config config;
    private final static Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws IOException, ParseException {
        Config config = new Config();
        if( args.length == 0 ){
            config.load_default();
        }else{
            config.load(args[0]);
        }

        JFrame frame = new ConfigWindow(config);
        frame.setVisible(true);
    }
}
