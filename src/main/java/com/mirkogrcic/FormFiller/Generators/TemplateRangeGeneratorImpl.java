package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Util.Range;

public class TemplateRangeGeneratorImpl implements RangeGenerator{
    private int index;
    private int[] array;

    public TemplateRangeGeneratorImpl(int[] array) {
        if( array.length < 2 ){
            throw new IllegalArgumentException("Array must have at least 2 elements");
        }

        this.index = 0;
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index+1 < array.length;
    }

    @Override
    public Range getNext() {
        return getAtIndex(index++);
    }

    @Override
    public Range getAtIndex(int index) {
        if( index+1 >= array.length ){
            return null;
        }
        return new Range(array[index], array[index+1]);
    }

    @Override
    public Range next() {
        return getNext();
    }
}
