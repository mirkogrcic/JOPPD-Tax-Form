package com.mirkogrcic.FormFiller.Util;

import javax.print.AttributeException;

public class Range {
    private int start;
    private int end;

    public Range(int start, int end){
        if( start > end ) {
            throw new IllegalArgumentException("Start cannot be larger than end");
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize(){
        return end - start;
    }

    public void setSizeStart(int size){
        int diff = size - this.getSize();
        this.start -= size;
    }

    public void setSizeEnd(int size){
        int diff = size - this.getSize();
        this.end += size;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", start, end);
    }
}
