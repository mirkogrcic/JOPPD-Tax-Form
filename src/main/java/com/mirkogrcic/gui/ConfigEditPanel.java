package com.mirkogrcic.gui;

import com.mirkogrcic.Config;
import com.mirkogrcic.ConfigMap;
import com.mirkogrcic.Locales.LocalizedText;
import com.mirkogrcic.utils.SpringUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

public class ConfigEditPanel extends JPanel {

    private Config config;
    private LocalizedText localizedText;

    private Map<String, JTextField> textFieldsMap;

    private final static Logger logger = Logger.getLogger(ConfigEditPanel.class.getName());
    private final static SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");

    public ConfigEditPanel(Config config){
        super(new SpringLayout());
        this.config = config;
        this.localizedText = LocalizedText.getInstance();
        this.textFieldsMap = new HashMap<>();

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
            textFieldsMap.put(command_names[i], tf);
        }

        SpringUtilities.makeCompactGrid(this, labels.length, 2, 6, 6, 6, 6);
    }

    private void updateFromConfig(){
        ConfigMap configMap = new ConfigMap(config.getConfigRaw());
        for( Map.Entry<String, JTextField> entry : textFieldsMap.entrySet() ){
            entry.getValue().setText(configMap.get(entry.getKey()));
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
                        config.setGrossIncome(Double.parseDouble(value));
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
                        config.setTax(Double.parseDouble(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e){
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "surtax":
                    try {
                        config.setSurtax(Double.parseDouble(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "pension1":
                    try {
                        config.setPension1(Double.parseDouble(value));
                        this.field.setForeground(Color.BLACK);
                    } catch (NumberFormatException e) {
                        this.field.setForeground(Color.RED);
                    }
                    break;
                case "pension2":
                    try {
                        config.setPension2(Double.parseDouble(value));
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
