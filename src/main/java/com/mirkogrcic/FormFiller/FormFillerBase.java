package com.mirkogrcic.FormFiller;


import com.mirkogrcic.FormFiller.Elements.Element;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class FormFillerBase<D> implements FormFiller<D> {
    protected Image image;
    protected Graphics graphics;
    protected Element[] elements;
    protected D data;
    private boolean drawn = false;

    public FormFillerBase(D data){
        this.data = data;
    }

    public void draw(){
        if( drawn )
            return;
        drawn = true;

        if( elements == null )
            return;

        for( int i = 0; i < elements.length; ++i ){
            Element element = elements[i];
            if( element == null )
                break;
            element.draw(graphics);
        }
    }

    @Override
    public void setData(D data) {
        this.data = data;
    }

    @Override
    public D getData() {
        return this.data;
    }

    public Image getImage() {
        this.draw();
        return image;
    }

    public void saveImage(String path){
        File file = new File(path);

        try {
            ImageIO.write((RenderedImage)image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
