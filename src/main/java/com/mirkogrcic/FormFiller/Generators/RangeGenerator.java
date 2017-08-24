package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Util.Range;

import java.util.Iterator;

public interface RangeGenerator extends Iterator<Range> {
    boolean hasNext();
    Range getNext();
    Range getAtIndex(int index);
}
