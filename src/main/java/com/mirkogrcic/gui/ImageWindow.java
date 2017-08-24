package com.mirkogrcic.gui;


import com.mirkogrcic.Config;
import com.mirkogrcic.calculator.Calculator.Result;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class ImageWindow extends JFrame {
    private final static Logger logger = Logger.getLogger(com.mirkogrcic.Application.class.getName());

    private Config config;
    private Result result;

    public ImageWindow(Image image){
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JOPPD page 1");

        this.getContentPane().add(new ImagePanel(image), BorderLayout.CENTER);
        //this.pack();

        this.setSize(500,500);
    }

    public ImageWindow(Config config, Result result){
        this.config = config;
        this.result = result;

        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JOPPD page 1");


        InputStream inStream = getClass().getResourceAsStream("/images/Obrazac_JOPPD_1.png");
        assert inStream != null;

        Image image;
        try {
            image = ImageIO.read(inStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Graphics graphics = image.getGraphics();
        Font font = new Font("TimesNewRoman", Font.BOLD, 30);

        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        graphics.drawString("Text to draw", 100, 100);


        this.getContentPane().add(new ImagePanel(image), BorderLayout.CENTER);
        //this.pack();

        this.setSize(500,500);



    }
}

