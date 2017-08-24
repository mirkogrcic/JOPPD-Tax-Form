package com.mirkogrcic.gui;

import com.mirkogrcic.gui.util.Point;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Logger;


public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private final static Logger logger = Logger.getLogger(com.mirkogrcic.Application.class.getName());
    private final static double initialScale = 0.3d;
    private final static double scaleStep = 0.05;
    private final static double scaleFineStep = 0.01;
    private final static double locXStep = 30d;
    private final static double locYStep = 30d;

    private Image image;
    private double scale;
    private Point location;  // Location in panel (center based)
    private Point frozenMotionLocation;   // Location in panel (center based)
    private Point mouseDownLocation;  // Location in panel (top-left based)

    private boolean fineScale;

    public ImagePanel(Image image){
        this.image = image;
        this.scale = initialScale;
        this.location = new Point();

        this.fineScale = false;

        this.setFocusable(true);
        this.requestFocus();

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        double iw, ih;  // Image
        double pw, ph;  // Panel
        double locx, locy;

        if( this.image != null ) {
            iw = image.getWidth(null) * scale;
            ih = image.getHeight(null) * scale;

            pw = this.getWidth();
            ph = this.getHeight();

            // Limit it so there is no empty space
            if( iw < pw ){
                // Image smaller than window, center it
                location.x = 0;
            }else{
                location.x = Math.min(location.x, iw/2 - pw/2);
                location.x = Math.max(location.x, -(iw/2 - pw/2));
            }
            if( ih < ph ){
                // Image smaller than window, center it
                location.y = 0;
            }else{
                location.y = Math.min(location.y, ih/2 - ph/2);
                location.y = Math.max(location.y, -(ih/2 - ph/2));
            }

            // Calculate location from center
            locx = location.x + pw/2 - iw/2;
            locy = location.y + ph/2 - ih/2;

            graphics.drawImage(this.image, (int)locx, (int)locy, (int)iw, (int)ih, null);
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_CONTROL:
                this.fineScale = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_CONTROL:
                this.fineScale = false;
                break;

            case KeyEvent.VK_R:
                this.scale = initialScale;
                this.location.reset();
                break;

            case KeyEvent.VK_PLUS:
                this.scale += fineScale ? scaleFineStep : scaleStep;
                break;
            case KeyEvent.VK_MINUS:
                this.scale -= fineScale ? scaleFineStep : scaleStep;
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                location.y -= locYStep;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                location.y += locYStep;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                location.x -= locXStep;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                location.x += locXStep;
                break;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        frozenMotionLocation = location.clone();
        mouseDownLocation = new Point(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        final double space = 0.8;
        final double iw = image.getWidth(null) * scale;
        final double ih = image.getHeight(null) * scale;
        final double pw = this.getWidth();
        final double ph = this.getHeight();

        final Point mouseLocation = new Point(mouseEvent.getX(), mouseEvent.getY());

        Point panelDiff = mouseLocation.getDiff(mouseDownLocation);

        location.set(frozenMotionLocation);
        location.add(panelDiff);

        this.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}


