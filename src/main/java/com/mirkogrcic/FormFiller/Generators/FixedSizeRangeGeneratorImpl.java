package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Util.Range;

public class FixedSizeRangeGeneratorImpl implements RangeGenerator {
    private int index;
    private int start;
    private int size;
    private int total;
    private boolean skipIncomplete;

    public FixedSizeRangeGeneratorImpl(int start, int size){
        this(start, size, 0);
    }

    public FixedSizeRangeGeneratorImpl(int start, int size, int total) {
        this(start, size, total, false);
    }

    public FixedSizeRangeGeneratorImpl(int start, int size, boolean skipIncomplete) {
        this(start, size, 0, skipIncomplete);
    }

    public FixedSizeRangeGeneratorImpl(int start, int size, int total, boolean skipIncomplete) {
        if( size < 1 ){
            throw new IllegalArgumentException("Size must be larger than zero");
        }
        if( total < size && total != 0 ){
            throw new IllegalArgumentException("MaxSize must be larger than Size");
        }

        this.index = 0;
        this.start = start;
        this.size = size;
        this.total = total;
        this.skipIncomplete = skipIncomplete;
    }

    @Override
    public boolean hasNext() {
        if( total == 0 ){
            // Infinite
            return true;
        }
        if( size*index >= total) {
            return false;
        }
        if( size*(index+1) > total && skipIncomplete ){
            return false;
        }
        return true;
    }

    @Override
    public Range getNext() {
        return getAtIndex(index++);
    }

    @Override
    public Range getAtIndex(int index) {
        if( total == 0 ){
            // Infinite
            return new Range(size*index, size*(index+1));
        }

        if( size*index >= total) {
            return null;
        }
        Range range = new Range(size*index, size*(index+1));
        if( range.getEnd() > total){
            if( skipIncomplete ){
                return null;
            }
            range.setEnd(total);
        }
        return range;
    }

    @Override
    public Range next() {
        return getNext();
    }
}
