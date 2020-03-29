package com.mirkogrcic.exporters;

import com.mirkogrcic.Data;
import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.TaxValuesImpl;
import org.junit.jupiter.api.Test;
import com.mirkogrcic.exporters.XmlExporter;
import org.xml.sax.SAXException;
import sun.util.calendar.BaseCalendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class XmlExporterTest {
    @Test
    void test1() {
        Data data = new Data();


        try {
            data.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-22"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        data.setGrossIncome(new BigDecimal(""));

        TaxValuesImpl taxes = new TaxValuesImpl();
        Calculator calc = new Calculator(BigDecimal.valueOf(0L), taxes);
        taxes.loadHumanReadable(
                data.getPension1(),
                data.getPension2(),
                data.getTax(),
                data.getSurtax()
        );
        calc.setGrossIncome(data.getGrossIncome());
        Calculator.Result result = calc.calculate();

        XmlExporter exporter = new XmlExporter(data, result);
        try {
            String text = exporter.exportString();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        try {
            exporter.exportFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
