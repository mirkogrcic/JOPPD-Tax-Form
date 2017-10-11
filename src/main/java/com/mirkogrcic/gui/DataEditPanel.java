package com.mirkogrcic.gui;

import com.mirkogrcic.Data;
import com.mirkogrcic.Locales.LocalizedText;
import com.mirkogrcic.utils.SpringUtilities;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataEditPanel extends JPanel {

    private final static Logger logger = LoggerFactory.getLogger(DataEditPanel.class.getName());
    private final static SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");

    private Data config;
    private LocalizedText localizedText;

    private List<JTextField> textFieldList;


    public DataEditPanel(Data config){
        super(new SpringLayout());
        this.config = config;
        this.localizedText = LocalizedText.getInstance();
        this.textFieldList = new ArrayList<>();

        final String[] labels = {
                localizedText.format("FullName") + ": ",
                localizedText.format("Address") + ": ",
                localizedText.format("Email") + ": ",
                localizedText.format("OIB") + ": ",
                localizedText.format("CityCodeResidence") + ": ",
                localizedText.format("CityCodeWork") + ": ",

                localizedText.format("GrossIncome") + ": ",
                localizedText.format("DateOfPayment") + "[YYYY/MM/DD]: ",

                localizedText.format("Tax") + "[%]: ",
                localizedText.format("Surtax") + "[%]: ",
                localizedText.format("Pension1") + "[%]: ",
                localizedText.format("Pension2") + "[%]: ",
        };

        final String[] command_names = {
                "fullName",
                "address",
                "email",
                "oib",
                "cityCodeResidence",
                "cityCodeWork",

                "grossIncome",
                "date",

                "tax",
                "surtax",
                "pension1",
                "pension2",
        };

        final String[] values = {
                config.getFullName(),
                config.getAddress(),
                config.getEmail(),
                config.getOib(),
                config.getCityCodeResidence(),
                config.getCityCodeWork(),

                config.getGrossIncome().toString(),
                dateFmt.format(config.getDate()),

                config.getTax().toString(),
                config.getSurtax().toString(),
                config.getPension1().toString(),
                config.getPension2().toString()
        };

        for( int i = 0; i < labels.length; ++i ){
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            this.add(l);

            JTextField tf = new JTextField(values[i],30);
            tf.getDocument().addDocumentListener(new TextFieldListener(command_names[i], tf));
            l.setLabelFor(tf);
            this.add(tf);
            textFieldList.add(tf);
        }

        SpringUtilities.makeCompactGrid(this, labels.length, 2, 6, 6, 6, 6);
    }

    public void updateFromConfig(){
        final String[] values = {
                config.getFullName(),
                config.getAddress(),
                config.getEmail(),
                config.getOib(),
                config.getCityCodeResidence(),
                config.getCityCodeWork(),

                config.getGrossIncome().toString(),
                dateFmt.format(config.getDate()),

                config.getTax().toString(),
                config.getSurtax().toString(),
                config.getPension1().toString(),
                config.getPension2().toString()
        };
        for( int i = 0; i < values.length; ++i ){
            JTextField tf = textFieldList.get(i);
            tf.setText(values[i]);
        }
    }

    private class TextFieldListener implements DocumentListener {

        private String command;
        private JTextField field;

        public TextFieldListener(String command, JTextField field){
            this.command = command;
            this.field = field;
        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            this.run(documentEvent);
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            this.run(documentEvent);
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            this.run(documentEvent);
        }

        private void run(DocumentEvent documentEvent){
            String command = this.command;
            final Document doc = documentEvent.getDocument();
            String value;
            try {
                value = doc.getText(0, doc.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
                return;
            }
            switch (command){
                case "fullName":
                    config.setFullName(value);
                    break;
                case "address":
                    config.setAddress(value);
                    break;
                case "email":
                    config.setEmail(value);
                    break;
                case "oib":
                    config.setOib(value);
                    break;
                case "cityCodeResidence":
                    config.setCityCodeResidence(value);
                    break;
                case "cityCodeWork":
                    config.setCityCodeWork(value);
                    break;
                case "grossIncome":
                    try {
                        config.setGrossIncome(new BigDecimal(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "date":
                    try {
                        config.setDate(dateFmt.parse(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (ParseException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "tax":
                    try {
                        config.setTax(new BigDecimal(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e){
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "surtax":
                    try {
                        config.setSurtax(new BigDecimal(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "pension1":
                    try {
                        config.setPension1(new BigDecimal(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "pension2":
                    try {
                        config.setPension2(new BigDecimal(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
            }
        }
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        logger.info("New locale set");
    }
}
