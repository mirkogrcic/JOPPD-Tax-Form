package com.mirkogrcic.gui;


import com.mirkogrcic.Config;
import com.mirkogrcic.Locales.LocalizedText;
import com.mirkogrcic.calculator.Calculator.Result;
import com.mirkogrcic.utils.SpringUtilities;
import com.mirkogrcic.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class ResultWindow extends JDialog {
    private final static Logger logger = Logger.getLogger(com.mirkogrcic.Application.class.getName());
    private Config config;
    private Result result;
    private LocalizedText localizedText;

    public ResultWindow(Config config, Result result){
        this(config, result, null);
    }

    public ResultWindow(Config config, Result result, Frame parent){
        super(parent, "Results", true);
        this.config = config;
        this.result = result;
        this.localizedText = LocalizedText.getInstance();

        int rounding = 2;

        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");
        DecimalFormat decimalFmt = new DecimalFormat("0.00");

        Double totalSumPercent = result.getTotalSum() / config.getGrossIncome() * 100;

        Double netIncomePercent = result.getNetIncome() / config.getGrossIncome() * 100;

        final String[] labels = {
                localizedText.format("Pension1") + ": ",
                localizedText.format("Pension2") + ": ",
                localizedText.formatString("%s + %s: ","Pension1", "Pension2"),
                String.format("%s - %s - %s: ","GrossIncome", "Pension1", "Pension2"),

                localizedText.format("Tax") + ": ",
                localizedText.format("Surtax") + ": ",
                localizedText.formatString("%s + %s: ", "Tax","Surtax"),

                String.format(localizedText.format("TotalTaxed") + ": %s%%", decimalFmt.format(totalSumPercent)),
                String.format(localizedText.format("NetIncome") + ": %s%%", decimalFmt.format(netIncomePercent)),
        };


        final Double[] values = {
                result.getPension1(),
                result.getPension2(),
                result.getPension(),
                result.getGrossPensionSub(),
                result.getTax(),
                result.getSurtax(),
                result.getTaxSurtaxSum(),
                result.getTotalSum(),
                result.getNetIncome(),
        };
        final String[] stringValues = new String[values.length];
        int maxLength = 0;
        for( int i = 0; i < values.length; ++i ){
            stringValues[i] = Util.formatDoubleHR(values[i]);
            maxLength = Math.max(maxLength, stringValues[i].length());
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel p = new JPanel(new SpringLayout());
        for( int i = 0; i < labels.length; ++i ){
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);

            String value = stringValues[i];
            JTextField tf = new JTextField(value,maxLength);
            tf.setHorizontalAlignment(SwingConstants.RIGHT);
            tf.setEditable(false);
            l.setLabelFor(tf);
            p.add(tf);
        }

        SpringUtilities.makeCompactGrid(p, labels.length, 2, 6, 6, 6, 6);

        mainPanel.add(p);

        // Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton calculateButton = new JButton("Exit");
        calculateButton.setActionCommand("Exit");
        calculateButton.addActionListener(new ButtonClickListener());

        buttonsPanel.add(calculateButton);

        mainPanel.add(buttonsPanel);

        this.getContentPane().add(mainPanel);
        this.pack();



    }

    private class ButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            switch (command){
                case "Exit":
                    setVisible(false);
                    break;
            }
        }
    }
}

