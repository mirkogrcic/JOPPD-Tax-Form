package com.mirkogrcic;


import com.mirkogrcic.utils.NumberFormatException;
import com.mirkogrcic.utils.Util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Config {

    private ConfigRaw configRaw;
    private SimpleDateFormat dateFmt;

    public Config(){
        this(new ConfigRaw());
    }

    public Config(ConfigRaw configRaw){
        this.configRaw = configRaw;
        dateFmt = new SimpleDateFormat("yyyy/MM/dd");
    }

    public void load_default() throws IOException, ParseException {
        configRaw.load_default();
        validate();
    }

    public void load(String filePath) throws IOException, ParseException {
        configRaw.load(filePath);
        validate();
    }

    public void load(InputStreamReader inStream) throws IOException, ParseException {
        configRaw.load(inStream);
        validate();
    }

    public void save(String filePath) throws IOException{
        configRaw.save(filePath);
    }

    public void save(OutputStreamWriter outStream) throws IOException {
        configRaw.save(outStream);
    }

    public void validate() {
        try {
            getGrossIncome();
        } catch (RuntimeException e) {
            throw new NumberFormatException("Invalid gross income value", e);
        }
        getDate();
        try{
            getPension1();
        } catch (RuntimeException e) {
            throw new NumberFormatException("Invalid pension 1 value", e);
        }
        try{
            getPension2();
        } catch (RuntimeException e) {
            throw new NumberFormatException("Invalid pension 2 value", e);
        }
        try{
            getTax();
        } catch (RuntimeException e) {
            throw new NumberFormatException("Invalid tax value", e);
        }
        try{
            getSurtax();
        } catch (RuntimeException e) {
            throw new NumberFormatException("Invalid surtax value value", e);
        }
    }

    public ConfigRaw getConfigRaw() {
        return configRaw;
    }

    public void setConfigRaw(ConfigRaw configRaw) {
        this.configRaw = configRaw;
    }

    public Double getGrossIncome() {
        return Double.parseDouble(configRaw.getGrossIncome());
    }

    public void setGrossIncome(Double grossIncome) {
        configRaw.setGrossIncome(grossIncome.toString());
    }

    public Date getDate() {
        try {
            return dateFmt.parse(configRaw.getDate());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    public void setDate(Date date) {
        configRaw.setDate(dateFmt.format(date));
    }

    public Double getPension1() {
        return Double.parseDouble(configRaw.getPension1());
    }

    public void setPension1(Double pension1) {
        configRaw.setPension1(pension1.toString());
    }

    public Double getPension2() {
        return Double.parseDouble(configRaw.getPension2());
    }

    public void setPension2(Double pension2) {
        configRaw.setPension2(pension2.toString());
    }

    public Double getTax() {
        return Double.parseDouble(configRaw.getTax());
    }

    public void setTax(Double tax) {
        configRaw.setTax(tax.toString());
    }

    public Double getSurtax() {
        return Double.parseDouble(configRaw.getSurtax());
    }

    public void setSurtax(Double surtax) {
        configRaw.setSurtax(surtax.toString());
    }

    public String getFullName() {
        return configRaw.getFullName();
    }

    public void setFullName(String fullName) {
        configRaw.setFullName(fullName);
    }

    public String getAddress() {
        return configRaw.getAddress();
    }

    public void setAddress(String address) {
        configRaw.setAddress(address);
    }

    public String getEmail() {
        return configRaw.getEmail();
    }

    public void setEmail(String email) {
        configRaw.setEmail(email);
    }

    public String getOib() {
        return configRaw.getOib();
    }

    public void setOib(String oib) {
        configRaw.setOib(oib);
    }

    public String getCityCodeResidence() {
        return configRaw.getCityCodeResidence();
    }

    public void setCityCodeResidence(String cityCodeResidence) {
        configRaw.setCityCodeResidence(cityCodeResidence);
    }

    public String getCityCodeWork() {
        return configRaw.getCityCodeWork();
    }

    public void setCityCodeWork(String cityCodeWork) {
        configRaw.setCityCodeWork(cityCodeWork);
    }
}
