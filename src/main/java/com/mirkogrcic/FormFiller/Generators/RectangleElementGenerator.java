package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Util.Axis;
import com.mirkogrcic.FormFiller.Util.Range;

import java.awt.*;

public class RectangleElementGenerator implements ElementGenerator<Rectangle>{
    private RangeGenerator rangeGenerator;
    private Axis dynamicAxis;
    private Range fixedAxisRange;

    public RectangleElementGenerator(RangeGenerator rangeGenerator, Axis dynamicAxis, Range fixedAxisRange) {
        this.rangeGenerator = rangeGenerator;
        this.dynamicAxis = dynamicAxis;
        this.fixedAxisRange = fixedAxisRange;
    }

    private Rectangle getRectangle(Range range){
        if( range == null ){
            return null;
        }
        switch (dynamicAxis){
            case AXIS_X:
                return new Rectangle(range.getStart(), fixedAxisRange.getStart(), range.getSize(), fixedAxisRange.getSize());
            case AXIS_Y:
                return new Rectangle(fixedAxisRange.getStart(), range.getStart(), fixedAxisRange.getSize(), range.getSize());
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return rangeGenerator.hasNext();
    }

    @Override
    public Rectangle getNext(){
        Range range = rangeGenerator.getNext();
        return getRectangle(range);
    }

    @Override
    public Rectangle getAtIndex(int index) {
        Range range = rangeGenerator.getAtIndex(index);
        return getRectangle(range);
    }

    @Override
    public Rectangle next() {
        return getNext();
    }
}
