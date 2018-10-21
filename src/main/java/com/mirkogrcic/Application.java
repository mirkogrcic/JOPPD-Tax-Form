package com.mirkogrcic;

import com.mirkogrcic.gui.DataWindow;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private Data data;
    private final static Logger logger = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] args) throws IOException, ParseException {
        BasicConfigurator.configure();
        Data data = new Data();
        if( args.length == 0 ){
            data.loadStream(System.class.getResourceAsStream("/data.ini"));
            logger.info("Loading default configuration");
        }else{
            try {
                data.load(args[0]);
            } catch (FileNotFoundException ex) {
                data.loadStream(System.class.getResourceAsStream("/data.ini"));
                logger.error("File not found", args[0]);
                logger.info("Loading default configuration");
            }
        }

        JFrame frame = new DataWindow(data);
        frame.setVisible(true);
    }
}
