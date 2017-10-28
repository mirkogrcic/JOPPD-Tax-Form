package com.mirkogrcic.Locales;

import com.mirkogrcic.utils.UTF8Control;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalizedText {
    private static Logger logger = LoggerFactory.getLogger(LocalizedText.class);
    private static LocalizedText instance;

    private Locale locale;
    private ResourceBundle messages;
    private MessageFormat fmt;
    private List<LocaleChangeListener> localeChangeListeners = new ArrayList<>();

    private LocalizedText() {
        this.fmt = new MessageFormat("");

        updateLocale();
        logger.info("Selected locale: {}-{}", locale.getISO3Country(), locale.getISO3Language());
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
        Locale oldLocale = fmt.getLocale();
        fmt.setLocale(locale);

        for (LocaleChangeListener localeChangeListener : localeChangeListeners) {
            localeChangeListener.localeChanged(oldLocale, locale);
        }
    }

    public Boolean localeChanged(){
        return instance.locale != Locale.getDefault(Locale.Category.DISPLAY);
    }

    public void addLocaleChangeListener(LocaleChangeListener localeChangeListener) {
        localeChangeListeners.add(localeChangeListener);
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

    public interface LocaleChangeListener {
        void localeChanged(Locale oldLocale, Locale newLocale);
    }
}
