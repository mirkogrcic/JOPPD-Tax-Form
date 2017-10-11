package com.mirkogrcic.gui;


import com.mirkogrcic.Data;
import com.mirkogrcic.calculator.Calculator.Result;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageWindow extends JFrame {
    private final static Logger logger = LoggerFactory.getLogger(com.mirkogrcic.Application.class.getName());

    private Data config;
    private Result result;

    public ImageWindow(Image image){
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JOPPD page 1");

        this.getContentPane().add(new ImagePanel(image), BorderLayout.CENTER);
        //this.pack();

        this.setSize(500,500);
    }

    public ImageWindow(Data config, Result result){
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

