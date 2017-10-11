package com.mirkogrcic;


import com.mirkogrcic.utils.NumberFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Data {

    private static final Logger logger = LoggerFactory.getLogger(Data.class);
    private static final SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");

    private Ini file;
    private Preferences prefs;

    // Personal
    private String fullName;
    private String address;
    private String email;
    private String oib;
    private String cityCodeResidence;
    private String cityCodeWork;

    // Tax
    private BigDecimal pension1;
    private BigDecimal pension2;
    private BigDecimal tax;
    private BigDecimal surtax;

    // Payment
    private Date date;
    private BigDecimal grossIncome;

    public Data() {
    }

    public Data(String filepath)
            throws IOException {
        load(filepath);
    }

    public Data(File file)
            throws IOException {
        load(file);
    }

    /**
     * @exception NumberFormatException
     * @exception DateTimeParseException
     * @exception IllegalStateException
     */
    public void load() {
        if (prefs == null)
            throw new IllegalStateException("No file loaded");

        Preferences section;

        section = prefs.node("Osobne");
        this.fullName = section.get("puno_ime", "");
        this.address = section.get("adresa", "");
        this.email = section.get("email", "");
        this.oib = section.get("oib", "");
        this.cityCodeResidence = section.get("sifra_opcine_grada_prebivalista_boravista", "");
        this.cityCodeWork = section.get("sifra_opcine_grada_rada", "");

        section = prefs.node("PorezneStope");
        this.pension1 = parseDecimal(section.get("stup1", ""));
        this.pension2 = parseDecimal(section.get("stup2", ""));
        this.tax = parseDecimal(section.get("porez", ""));
        this.surtax = parseDecimal(section.get("prirez", ""));

        section = prefs.node("Placa");
        this.date = parseDate(section.get("datum_primitka", ""));
        this.grossIncome = parseDecimal(section.get("bruto_placa", ""));
    }

    public void loadStream(InputStream stream)
            throws IOException {
        this.file = new Ini(stream);
        this.prefs = new IniPreferences(this.file);
        load();
    }

    public void load(File file)
            throws IOException {
        this.file = new Ini(file);
        this.prefs = new IniPreferences(this.file);
        load();
    }

    public void load(String filepath)
            throws IOException {
        load(new File(filepath));
    }

    private void updatePrefs() {
        if (prefs == null)
            throw new IllegalStateException("No file loaded");

        Preferences section;

        section = prefs.node("Osobne");
        section.put("puno_ime", fullName);
        section.put("adresa", address);
        section.put("email", email);
        section.put("oib", oib);
        section.put("sifra_opcine_grada_prebivalista_boravista", cityCodeResidence);
        section.put("sifra_opcine_grada_rada", cityCodeWork);

        section = prefs.node("PorezneStope");
        section.put("stup1", pension1.toString());
        section.put("stup2", pension2.toString());
        section.put("porez", tax.toString());
        section.put("prirez", surtax.toString());

        section = prefs.node("Placa");
        section.put("datum_primitka", dateFmt.format(date));
        section.put("bruto_placa", grossIncome.toString());
    }

    public void save()
            throws IOException {
        updatePrefs();
        this.file.store();
    }

    public void save(File file)
            throws IOException {
        if (this.file == null) {
            this.file = new Ini(file);
        }
        if (this.prefs == null) {
            this.prefs = new IniPreferences(this.file);
        }
        updatePrefs();
        this.file.store(file);
    }

    public void save(String filepath)
            throws IOException {
        save(new File(filepath));
    }

    public void saveStream(OutputStream stream)
            throws IOException, BackingStoreException {
        if (prefs == null) {
            file = new Ini();
            prefs = new IniPreferences(file);
        }
        prefs.exportNode(stream);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getCityCodeResidence() {
        return cityCodeResidence;
    }

    public void setCityCodeResidence(String cityCodeResidence) {
        this.cityCodeResidence = cityCodeResidence;
    }

    public String getCityCodeWork() {
        return cityCodeWork;
    }

    public void setCityCodeWork(String cityCodeWork) {
        this.cityCodeWork = cityCodeWork;
    }

    public BigDecimal getPension1() {
        return pension1;
    }

    public void setPension1(BigDecimal pension1) {
        this.pension1 = pension1;
    }

    public BigDecimal getPension2() {
        return pension2;
    }

    public void setPension2(BigDecimal pension2) {
        this.pension2 = pension2;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getSurtax() {
        return surtax;
    }

    public void setSurtax(BigDecimal surtax) {
        this.surtax = surtax;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    private BigDecimal parseDecimal(String value) {
        return new BigDecimal(value);
    }

    private Date parseDate(String value) {
        try {
            return dateFmt.parse(value);
        } catch (ParseException e) {
            throw new DateTimeParseException("Error parsing config parameter Osobne.datum_primitka", value, -1, e);
        }
    }
}
