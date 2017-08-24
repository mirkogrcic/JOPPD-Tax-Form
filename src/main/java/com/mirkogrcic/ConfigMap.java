package com.mirkogrcic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class ConfigMap implements Map<String, String> {
    private static final String[] keys = new String[]{
            "grossIncome",
            "date",
            "pension1",
            "pension2",
            "tax",
            "surtax",
            "fullName",
            "address",
            "email",
            "oib",
            "cityCodeResidance",
            "cityCodeWork"
    };
    private ConfigRaw configRaw;

    public ConfigMap(ConfigRaw configRaw) {
        this.configRaw = configRaw;
    }

    private String getValue(String key){
        switch (key){
            case "grossIncome": return configRaw.getGrossIncome();
            case "date": return configRaw.getDate();
            case "pension1": return configRaw.getPension1();
            case "pension2": return configRaw.getPension2();
            case "tax": return configRaw.getTax();
            case "surtax": return configRaw.getSurtax();
            case "fullName": return configRaw.getFullName();
            case "address": return configRaw.getAddress();
            case "email": return configRaw.getEmail();
            case "oib": return configRaw.getOib();
            case "cityCodeResidence": return configRaw.getCityCodeResidence();
            case "cityCodeWork": return configRaw.getCityCodeWork();
            default:
                throw new IllegalArgumentException("Invalid key: " + key);
        }
    }

    private void setValue(String key, String value){
        switch (key){
            case "grossIncome": configRaw.setGrossIncome(value);
            case "date": configRaw.setDate(value);
            case "pension1": configRaw.setPension1(value);
            case "pension2": configRaw.setPension2(value);
            case "tax": configRaw.setTax(value);
            case "surtax": configRaw.setSurtax(value);
            case "fullName": configRaw.setFullName(value);
            case "address": configRaw.setAddress(value);
            case "email": configRaw.setEmail(value);
            case "oib": configRaw.setOib(value);
            case "cityCodeResidence": configRaw.setCityCodeResidence(value);
            case "cityCodeWork": configRaw.setCityCodeWork(value);
            default:
                throw new IllegalArgumentException("Invalid key: " + key);
        }
    }

    @Override
    public int size() {
        return keys.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        return Arrays.stream(keys).anyMatch(x -> x == o);
    }

    @Override
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(Object o) {
        return getValue((String) o);
    }

    @Override
    public String put(String s, String s2) {
        setValue(s, s2);
        return s2;
    }

    @Override
    public String remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map) {

    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        return new HashSet<>(Arrays.asList(keys));
    }

    @Override
    public Collection<String> values() {
        Collection<String> result = new ArrayList<>();
        for( String key : keys ) {
            String value = getValue(key);
            result.add(value);
        }
        return result;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> result = new HashSet<>();
        for( String key : keys ){
            String value = getValue(key);
            result.add(new Entry<String, String>() {
                @Override
                public String getKey() {
                    return key;
                }

                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public String setValue(String s) {
                    ConfigMap.this.setValue(key, s);
                    return s;
                }
            });
        }
        return result;
    }
}
