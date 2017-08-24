package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Util.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedCountRangeGeneratorImplTest {
    @Test
    void test1() {
        RangeGenerator rangeGenerator = new FixedCountRangeGeneratorImpl(0, 30, 3);
        Range result;

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{0, 10}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{10, 20}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{20, 30}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(false, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertEquals(null, result);
    }

    @Test
    void test2() {
        RangeGenerator rangeGenerator = new FixedCountRangeGeneratorImpl(0, 5, 3);
        Range result;

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{0, 1}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{1, 2}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{2, 3}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(false, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertEquals(null, result);
    }

    @Test
    void test3() {
        RangeGenerator rangeGenerator = new FixedCountRangeGeneratorImpl(0, 30, 2);
        Range result;

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{0, 15}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(true, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertArrayEquals(new int[]{15, 30}, new int[]{result.getStart(), result.getEnd()});

        assertEquals(false, rangeGenerator.hasNext());
        result = rangeGenerator.getNext();
        assertEquals(null, result);
    }

}