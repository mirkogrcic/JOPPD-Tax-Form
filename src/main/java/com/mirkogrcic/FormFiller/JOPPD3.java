package com.mirkogrcic.FormFiller;


import com.mirkogrcic.FormFiller.Data.JOPPD3Data;
import com.mirkogrcic.FormFiller.Elements.TextElement;
import com.mirkogrcic.FormFiller.Generators.ElementGenerator;
import com.mirkogrcic.FormFiller.Generators.RangeGenerator;
import com.mirkogrcic.FormFiller.Generators.RectangleElementGenerator;
import com.mirkogrcic.FormFiller.Generators.TemplateRangeGeneratorImpl;
import com.mirkogrcic.FormFiller.Util.Axis;
import com.mirkogrcic.FormFiller.Util.Range;
import com.mirkogrcic.utils.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class JOPPD3 extends FormFillerBase<JOPPD3Data> {

    // Constructor
    public JOPPD3(JOPPD3Data data){
        super(data);

        // Read image and initialize
        image = Util.getImageResource("/images/Obrazac_JOPPD_3.png");
        graphics = image.getGraphics();
        elements = new TextElement[50];

        Font font1 = new Font("TimesNewRoman", Font.PLAIN, 25);

        // Add text elements
        int currentIndex = 0;
        TextElement currentElement;
        // date - 1230 x 440 - 1480 x 470
        // box1 - 1000 x 673 - 1700 x 920 - hcount(5)
        // vrsta izvješća - 2210 x 520 - 2440 x 610

        final int rcx = 2210, rcx2 = 2440;  // Leftmost column

        SimpleDateFormat dateFmt = new SimpleDateFormat("dd.MM.yyyy.");

        // GROUP header row
        final int fy = 250, fy2 = 285;

        // OIB
        currentElement = new TextElement(data.getSubmitterOIB(), Util.getRectangle(410, fy, 600, fy2), font1);
        elements[currentIndex++] = currentElement;

        // Oznaka izvješća
        currentElement = new TextElement(data.getDateMark(), Util.getRectangle(940, fy, 1070, fy2), font1);
        elements[currentIndex++] = currentElement;

        // Vrsta izvješća
        currentElement = new TextElement("8", Util.getRectangle(1407, fy, 1510, fy2), font1);
        elements[currentIndex++] = currentElement;

        // Broj stranice
        currentElement = new TextElement("1", Util.getRectangle(2977, fy, 3010, fy2), font1);
        elements[currentIndex++] = currentElement;

        // Broj stranica
        currentElement = new TextElement("1", Util.getRectangle(3022, fy, 3080, fy2), font1);
        elements[currentIndex++] = currentElement;

        // Table redni broj
        currentElement = new TextElement("1", Util.getRectangle(106, 655, 180, 921), font1);
        elements[currentIndex++] = currentElement;


        // GRUPA Table Row 1
        int ry = 655, ry2 = 788;  // Row
        int[] columns = new int[]{180, 330, 562, 730, 910, 1088, 1342, 1520, 1695, 1875, 2067, 2285, 2433, 2575, 2730, 2907, 3085, 3265, 3411};
        RangeGenerator rangeGenerator = new TemplateRangeGeneratorImpl(columns);
        ElementGenerator<Rectangle> elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(ry, ry2));

        Rectangle currentCell;

        // 2. Šifra općine/grada prebivališta
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getCityCodeResidence(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 4. OIB
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSubmitterOIB(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 6.1. Oznaka stjecatelja/osiguranika
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("4002", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 7.1. Obveza dodatnog doprinosa za MO za staž s povećanim trajanjem
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 8. Oznaka prvog/zadnjeg mjeseca u osiguranju po istoj osnovi
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 10. Sati rada
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(" ", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 11. Iznos oporezivog primitka
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getGrossIncome(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.1. Doprinos za mirovinsko osiguranje, 1. Stup
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getPension1(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.3.Doprinos za zdravstveno osiguranje
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.5. Doprinos za zapošljavanje
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.7. Dodatni doprinos zua mirovinsko osiguranje koji se računa s povećanim trajanjem - II Stup
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.9. Poseban doprinos za zapošljavanje osobas invaliditetom
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 13.2. Izdatak - uplaćeni doprinos za mirovinsko osiguranje
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getPension(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 13.4. Osobni odbitak
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 14.1. Iznos obračunatog poreza na dohodak
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getTax(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 15.1. Oznaka neoporezivog primitka
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 16.1. Oznaka načina isplate
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("2", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 17. Obračunani od nesamostalnog rada (plaća)
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(Util.formatDoubleHR(0d), currentCell, font1);
        elements[currentIndex++] = currentElement;


        // GRUPA Table Row 2
        ry = 788; ry2 = 921;  // Row
        columns = new int[]{180, 330, 562, 730, 910, 1088, 1210, 1342, 1520, 1695, 1875, 2067, 2285, 2433, 2575, 2730, 2907, 3085, 3265, 3411};
        rangeGenerator = new TemplateRangeGeneratorImpl(columns);
        elementGenerator = new RectangleElementGenerator(rangeGenerator, Axis.AXIS_X, new Range(ry, ry2));

        // 3. Šifra općine/grada rada
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getCityCodeWork(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 5. Ime i prezime stjecatelja/osiguranika
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSubmitterFullName(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 6.2 Oznaka primitka/obveze doprinosa
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("4025", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 7.2 Obveza posebnog doprinosa za poticanje zapošljavanja osoba s invaliditetom
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 9. Oznaka punog/nepunog radnog vremena ili rada s polovicom radnog vremena
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 10.1. Razdoblje obračuna od
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("01.01.", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 10.2. Razdoblje obračuna do
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("31.12.", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12. Osnovica za obračun doprinosa
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getGrossIncome(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.2. Doprinos za mirovinsko osiguranje - II STUP
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getPension2(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.4. Doprinos za zaštitu zdravlja na radu
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.6. Dodatni doprinos za mirovinsko osiguranje za staž osiguranja koji se računa s povećanim trajanjem
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 12.8. Poseban doprinos za korištenje zdravstvene zaštite u inozemstvu
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 13.1. Izdatak
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 13.3. Dohodak
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getGrossPensionSub(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 13.5. Porezna osnovica
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getGrossPensionSub(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 14.2. Iznos obračunatog prireza porezu na dohodak
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getSurtax(), currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 15.2. Iznos neoporezivog primitka
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement("0", currentCell, font1);
        elements[currentIndex++] = currentElement;

        // 16.2 Iznos za isplatu
        currentCell = elementGenerator.getNext();
        currentElement = new TextElement(data.getNetIncome(), currentCell, font1);
        elements[currentIndex++] = currentElement;

    }
}
