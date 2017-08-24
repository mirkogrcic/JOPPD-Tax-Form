package com.mirkogrcic.Locales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Locales {
    private static List<LocaleInfo> locales = new ArrayList<>();
    static {
        locales.add(new LocaleInfo(
                new Locale("hr", "HR"),
                "Hrvatski"
        ));
        locales.add(new LocaleInfo(
                new Locale("en", "US"),
                "English US"
        ));
        for( int i = 0; i < locales.size(); ++i ){
            locales.get(i).index = i;
        }
    }

    public static List<LocaleInfo> getLocales(){
        return Collections.unmodifiableList(locales);
    }

    public static LocaleInfo getByTitle(String title){
        for( LocaleInfo localeInfo : locales ){
            if( localeInfo.getTitle().equals(title) ){
                return localeInfo;
            }
        }

        return null;
    }

    public static LocaleInfo getByLocale(Locale locale){
        for( LocaleInfo localeInfo : locales ){
            if( localeInfo.getLocale() == locale ){
                return localeInfo;
            }
        }

        return null;
    }

    public static LocaleInfo getByIndex(int index){
        if( index < 0 || index > locales.size() ){
            return null;
        }

        return locales.get(index);
    }

    public static List<String> getTitles(){
        List<String> titles = new ArrayList<>(locales.size());
        for( LocaleInfo localeInfo : locales ){
            titles.add(localeInfo.getTitle());
        }
        return titles;
    }

    public static List<Locale> getAllLocales(){
        List<Locale> allLocales = new ArrayList<>(locales.size());
        for( LocaleInfo localeInfo : locales ){
            allLocales.add(localeInfo.getLocale());
        }
        return allLocales;
    }

    public static class LocaleInfo {
        private Locale locale;
        private String title;
        private int index;

        public LocaleInfo(Locale locale, String title) {
            this.locale = locale;
            this.title = title;
            this.index = 0;
        }

        public Locale getLocale() {
            return locale;
        }


        public String getTitle() {
            return title;
        }


        public int getIndex() {
            return index;
        }

    }
}
