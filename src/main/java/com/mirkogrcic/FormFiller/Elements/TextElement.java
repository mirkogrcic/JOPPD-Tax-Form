package com.mirkogrcic.FormFiller.Elements;

import com.mirkogrcic.Application;
import com.mirkogrcic.FormFiller.DrawPosition;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TextElement implements BoxElement{
    private String text;
    private Rectangle box;
    private DrawPosition drawPosition;
    private Color color;
    private Font font;

    public TextElement(String text, Rectangle box){
        this(text, box, new Font("TimesNewRoman", Font.PLAIN, 40));
    }

    public TextElement(String text, Rectangle box, Font font){
        this(text, box, font, Color.BLACK);
    }

    public TextElement(String text, Rectangle box, Font font, Color color){
        this.text = text;
        this.box = box;
        this.drawPosition = DrawPosition.CENTER;
        this.font = font;
        this.color = color;
    }

    public void drawCenter(Graphics graphics){
        Graphics2D graphics2d = (Graphics2D) graphics.create();

        if( !text.equals("") ) {
            FontRenderContext context = graphics2d.getFontRenderContext();
            TextLayout textLayout = new TextLayout(text, font, context);

            Rectangle2D bounds = textLayout.getBounds();
            double x, y;

            x = box.getCenterX() - bounds.getCenterX();
            y = box.getCenterY() - (bounds.getHeight() - textLayout.getDescent()) / 2;
            y += textLayout.getAscent() - textLayout.getDescent();

            graphics2d.setFont(font);
            graphics2d.setColor(color);
            graphics2d.drawString(text, (int) x, (int) y);
        }

        // DEBUGGING
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream("/config.properties") ) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String boundingBoxes = properties.getProperty("com.mirkogrcic.formfiller.boundingboxes");
        if( boundingBoxes.equals("true") ) {
            graphics2d.setColor(Color.RED);
            graphics2d.setStroke(new BasicStroke(1));
            graphics2d.drawRect((int) box.getX(), (int) box.getY(), (int) (box.getWidth()), (int) box.getHeight());
        }
    }

    public void draw(Graphics graphics){
        draw(graphics, this.drawPosition);
    }

    public void draw(Graphics graphics, DrawPosition drawPosition){
        switch (drawPosition){
            case CENTER:
                drawCenter(graphics);
                break;

            default:
                throw new UnsupportedOperationException(String.format(" Draw position '%s' not supported", drawPosition));
        }
    }

    @Override
    public Rectangle getBox() {
        return this.box;
    }

    @Override
    public void setBox(Rectangle box) {
        this.box = box;
    }

    @Override
    public DrawPosition getDrawPosition() {
        return this.drawPosition;
    }

    @Override
    public void setDrawPosition(DrawPosition drawPosition) {
        this.drawPosition = drawPosition;
    }
}
