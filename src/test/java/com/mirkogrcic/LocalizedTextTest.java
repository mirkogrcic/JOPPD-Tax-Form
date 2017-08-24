package com.mirkogrcic;

import com.mirkogrcic.Locales.LocalizedText;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class LocalizedTextTest {
    @Test
    void format() {
        Locale.setDefault(Locale.Category.DISPLAY, new Locale("hr", "HR"));
        LocalizedText localizedText = LocalizedText.getInstance();
        String rs;

        rs = localizedText.format("test");
        assertEquals("Ovo se koristi jedino {0} za testiranje čćž", rs);

        rs = localizedText.format("test", 3);
        assertEquals("Ovo se koristi jedino 3 za testiranje čćž", rs);
    }

    @Test
    void format1() {
        Locale.setDefault(Locale.Category.DISPLAY, new Locale("en", "US"));
        LocalizedText localizedText = LocalizedText.getInstance();
        String rs;

        rs = localizedText.format("test");
        assertEquals("This is used only {0} for testing", rs);

        rs = localizedText.format("test", 3);
        assertEquals("This is used only 3 for testing", rs);
    }

}