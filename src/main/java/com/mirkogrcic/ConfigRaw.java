package com.mirkogrcic;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConfigRaw{

    private Properties properties;

    public void load_default() throws IOException, ParseException {
        InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
        this.load(new InputStreamReader(inputStream));
    }

    public void load(String filePath) throws IOException, ParseException {
        File file = new File(filePath);
        InputStream inStream = new FileInputStream(file);
        InputStreamReader inStreamReader = new InputStreamReader(inStream, "UTF8");
        this.load(inStreamReader);
    }

    public void load(InputStreamReader inStream) throws IOException, ParseException {
        properties = new Properties();
        properties.load(inStream);

        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");
    }

    public void save(String filePath) throws IOException{
        File file = new File(filePath);
        OutputStream outStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF8");
        this.save(writer);
    }

    public void save(OutputStreamWriter outStream) throws IOException {
        properties.store((Writer)outStream, "UTF8");
    }

    public String getGrossIncome() {
        return properties.getProperty("bruto_placa");
    }

    public void setGrossIncome(String grossIncome) {
        properties.setProperty("bruto_placa", grossIncome);
    }

    public String getDate() {
        return properties.getProperty("datum_primitka");
    }

    public void setDate(String date) {
        properties.setProperty("datum_primitka", date);
    }

    public String getPension1() {
        return properties.getProperty("porez_stup1");
    }

    public void setPension1(String pension1) {
        properties.setProperty("porez_stup1", pension1);
    }

    public String getPension2() {
        return properties.getProperty("porez_stup2");
    }

    public void setPension2(String pension2) {
        properties.setProperty("porez_stup2", pension2);
    }

    public String getTax() {
        return properties.getProperty("porez_porez");
    }

    public void setTax(String tax) {
        properties.setProperty("porez_porez", tax);
    }

    public String getSurtax() {
        return properties.getProperty("porez_prirez");
    }

    public void setSurtax(String surtax) {
        properties.setProperty("porez_prirez", surtax);
    }

    public String getFullName() {
        return properties.getProperty("puno_ime");
    }

    public void setFullName(String fullName) {
        properties.setProperty("puno_ime", fullName);
    }

    public String getAddress() {
        return properties.getProperty("adresa");
    }

    public void setAddress(String address) {
        properties.setProperty("adresa", address);
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public void setEmail(String email) {
        properties.setProperty("email", email);
    }

    public String getOib() {
        return properties.getProperty("oib");
    }

    public void setOib(String oib) {
        properties.setProperty("oib", oib);
    }

    /**
     *
     * @return Sifra Opcine/Grada Prebivališta/Boravišta
     */
    public String getCityCodeResidence() {
        return properties.getProperty("sifra_opcine_grada_prebivalista_boravista");
    }

    public void setCityCodeResidence(String cityCodeResidence) {
        properties.setProperty("sifra_opcine_grada_prebivalista_boravista", cityCodeResidence);
    }

    /**
     *
     * @return Sifra Opcine/Grada Rada
     */
    public String getCityCodeWork() {
        return properties.getProperty("sifra_opcine_grada_rada");
    }

    public void setCityCodeWork(String cityCodeWork) {
        properties.setProperty("sifra_opcine_grada_rada", cityCodeWork);
    }
}
