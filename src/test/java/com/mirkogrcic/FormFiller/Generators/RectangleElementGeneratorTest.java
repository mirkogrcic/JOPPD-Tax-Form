package com.mirkogrcic.FormFiller.Generators;

import com.mirkogrcic.FormFiller.Generators.ElementGenerator;
import com.mirkogrcic.FormFiller.Generators.FixedSizeRangeGeneratorImpl;
import com.mirkogrcic.FormFiller.Generators.RangeGenerator;
import com.mirkogrcic.FormFiller.Generators.RectangleElementGenerator;
import com.mirkogrcic.FormFiller.Generators.TemplateRangeGeneratorImpl;
import com.mirkogrcic.FormFiller.Util.Axis;
import com.mirkogrcic.FormFiller.Util.Range;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangleElementGeneratorTest {
    @Test
    void test1() {
        RangeGenerator rangeGenerator = new FixedSizeRangeGeneratorImpl(0,10, 30, false);
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(30, 50));
        Rectangle result;

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{0, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{10, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{20, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(false, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertEquals(null, result);
    }

    @Test
    void test2() {
        RangeGenerator rangeGenerator = new FixedSizeRangeGeneratorImpl(0,10, 30, true);
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(30, 50));
        Rectangle result;

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{0, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{10, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{20, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(false, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertEquals(null, result);
    }

    @Test
    void test3() {
        RangeGenerator rangeGenerator = new FixedSizeRangeGeneratorImpl(0,10, 35, false);
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(30, 50));
        Rectangle result;

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{0, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{10, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{20, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{30, 30, 5, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(false, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertEquals(null, result);
    }

    @Test
    void test4() {
        RangeGenerator rangeGenerator = new FixedSizeRangeGeneratorImpl(0,10, 35, true);
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(30, 50));
        Rectangle result;

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{0, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{10, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{20, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(false, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertEquals(null, result);
    }

    @Test
    void test5() {
        RangeGenerator rangeGenerator = new TemplateRangeGeneratorImpl(new int[]{0, 10, 20, 30});
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(30, 50));
        Rectangle result;

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{0, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{10, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(true, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertArrayEquals(new int[]{20, 30, 10, 20}, new int[]{result.x, result.y, result.width, result.height});

        assertEquals(false, elementGenerator.hasNext());
        result = elementGenerator.getNext();
        assertEquals(null, result);
    }
}