package com.mirkogrcic.gui.util;

public class Point implements Cloneable{
    public double x;
    public double y;

    public Point(){
        this(0d, 0d);
    }

    public Point(Point value){
        this.set(value);
    }

    public Point(double x, double y){
        this.set(x, y);
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void set(Point value){
        x = value.x;
        y = value.y;
    }

    public void reset(){
        this.x = this.y = 0;
    }

    public void add(double value){
        this.x += value;
        this.y += value;
    }

    public void add(Point value){
        x += value.x;
        y += value.y;
    }

    public void sub(double value){
        this.x -= value;
        this.y -= value;
    }

    public void multiply(double value){
        this.x *= value;
        this.y *= value;
    }

    public void divide(double value){
        this.x /= value;
        this.y /= value;
    }

    public Point getDiff(Point value){
        return new Point(x - value.x, y - value.y);
    }

    public Point clone(){
        return new Point(x, y);
    }
}
