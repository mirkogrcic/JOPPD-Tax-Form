package com.mirkogrcic.FormFiller;


import com.mirkogrcic.FormFiller.Data.JOPPD1Data;
import com.mirkogrcic.FormFiller.Elements.TextElement;
import com.mirkogrcic.FormFiller.Generators.ElementGenerator;
import com.mirkogrcic.FormFiller.Generators.FixedCountRangeGeneratorImpl;
import com.mirkogrcic.FormFiller.Generators.RangeGenerator;
import com.mirkogrcic.FormFiller.Generators.RectangleElementGenerator;
import com.mirkogrcic.FormFiller.Util.Axis;
import com.mirkogrcic.FormFiller.Util.Range;
import com.mirkogrcic.utils.Util;

import java.awt.*;
import java.text.SimpleDateFormat;

public class JOPPD1 extends FormFillerBase<JOPPD1Data>{

    // Constructor
    public JOPPD1(JOPPD1Data data){
        super(data);

        // Read image and initialize
        image = Util.getImageResource("/images/Obrazac_JOPPD_1.png");
        graphics = image.getGraphics();
        elements = new TextElement[50];

        // Add text elements
        int currentIndex = 0;
        TextElement currentElement;
        // date - 1230 x 440 - 1480 x 470
        // box1 - 1000 x 673 - 1700 x 920 - hcount(5)
        // vrsta izvješća - 2210 x 520 - 2440 x 610

        final int rcx = 2210, rcx2 = 2440;  // Leftmost column

        SimpleDateFormat dateFmt = new SimpleDateFormat("dd.MM.yyyy.");


        // CELL Datum primitka

        currentElement = new TextElement(data.getDate(), Util.getRectangle(1230, 440, 1480, 470));
        elements[currentIndex++] = currentElement;

        // CELL Oznaka izvješća
        currentElement = new TextElement(data.getReportMark(), Util.getRectangle(825, 520, 990, 610));
        elements[currentIndex++] = currentElement;

        // CELL Broj osoba
        currentElement = new TextElement("1", Util.getRectangle(1000, 930, 1125, 1020));
        elements[currentIndex++] = currentElement;

        // GRUPA Osobni podaci
        Rectangle bounds = Util.getRectangle(1000, 673, 1700, 920);
        RangeGenerator rangeGenerator = new FixedCountRangeGeneratorImpl(bounds.y, (int)bounds.getMaxY(), 5);
        Rectangle currentCell;
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_Y, new Range(1000, 1700));

        // Puno ime
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSubmitterFullName(), currentCell);
        elements[currentIndex++] = currentElement;

        // Adresa
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSubmitterAddress(), currentCell);
        elements[currentIndex++] = currentElement;

        // Email
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSubmitterEmail(), currentCell);
        elements[currentIndex++] = currentElement;

        // OIB
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSubmitterOIB(), currentCell);
        elements[currentIndex++] = currentElement;

        // Oznaka podnositelja
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("4", currentCell);
        elements[currentIndex++] = currentElement;

        // CELL Vrsta izvješća
        currentElement = new TextElement("8", Util.getRectangle(rcx, 520, rcx2, 610));
        elements[currentIndex++] = currentElement;

        // CELL Broj redaka, obračun, stranica B
        currentElement = new TextElement("1", Util.getRectangle(rcx, 925, rcx2, 1020));
        elements[currentIndex++] = currentElement;

        // CELL V.5. Porez + Prirez
        currentElement = new TextElement(data.getTaxSurtaxSum(), Util.getRectangle(rcx, 1690, rcx2, 1785));
        elements[currentIndex++] = currentElement;

        // CELL VI.1.2. Mirovinsko 1. Stup
        currentElement = new TextElement(data.getPension1(), Util.getRectangle(rcx, 2115, rcx2, 2205));
        elements[currentIndex++] = currentElement;

        // CELL VI.2.2. Mirovinsko 2. Stup
        currentElement = new TextElement(data.getPension2(), Util.getRectangle(rcx, 2887, rcx2, 2982));
        elements[currentIndex++] = currentElement;

    }
}
