package com.mirkogrcic.FormFiller.Generators;

import java.util.Iterator;

public interface ElementGenerator<T> extends Iterator<T> {
    boolean hasNext();
    T getNext();
    T getAtIndex(int index);
}
