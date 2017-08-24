package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Util.Range;
import com.mirkogrcic.utils.Util;

/**
 *  Generates count ranges from start to end
 *  Can also generate end < start
 */
public class FixedCountRangeGeneratorImpl implements RangeGenerator {
    private int index;
    private int start;
    private int end;
    private int count;

    private double rangeSize;

    public FixedCountRangeGeneratorImpl(int start, int end, int count) {
        if( count < 0 ){
            throw new IllegalArgumentException("Count must be positive");
        }

        this.start = start;
        this.end = end;
        this.count = count;

        rangeSize = (start - end) / count;
        if( rangeSize < 0 )
            rangeSize *= -1;

    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public Range getNext() {
        return getAtIndex(index++);
    }

    @Override
    public Range getAtIndex(int index) {
        if( index >= count )
            return null;
        double start = this.start + rangeSize * ((double) index);
        double end = this.start + rangeSize * (((double)index) + 1);
        return new Range((int)Math.round(start), (int)Math.round(end));
    }

    @Override
    public Range next() {
        return getNext();
    }
}
