package com.mirkogrcic.Locales;

import com.mirkogrcic.utils.UTF8Control;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LocalizedText {
    private static LocalizedText instance;

    Locale locale;
    ResourceBundle messages;
    MessageFormat fmt;

    private LocalizedText() {
        this.fmt = new MessageFormat("");

        updateLocale();

        System.out.println(String.format("Selected locale: %s-%s", locale.getISO3Country(), locale.getISO3Language()));
    }

    public String format(String fmtName) {
        return format(fmtName, new Object[0]);
    }

    public String format(String fmtName, Object... fmtArgs) {
        fmt.applyPattern(messages.getString(fmtName));
        return fmt.format(fmtArgs);
    }

    public String formatString(String fmt, String... keyNames) {
        String[] localizationValues = new String[keyNames.length];
        for( int i = 0; i < keyNames.length; ++i ){
            localizationValues[i] = messages.getString(keyNames[i]);
        }
        return String.format(fmt, localizationValues);
    }

    public void updateLocale(){
        locale = Locale.getDefault(Locale.Category.DISPLAY);
        messages = ResourceBundle.getBundle("lang", locale, new UTF8Control());
        fmt.setLocale(locale);
    }

    public Boolean localeChanged(){
        return instance.locale != Locale.getDefault(Locale.Category.DISPLAY);
    }

    public static LocalizedText getInstance(){
        if( instance == null ){
            synchronized (LocalizedText.class){
                if( instance == null ){
                    instance = new LocalizedText();
                }
            }
        }
        if( instance.localeChanged() ){
            instance.updateLocale();
        }
        return instance;
    }
}
