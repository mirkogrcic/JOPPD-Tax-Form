package com.mirkogrcic.gui;


import com.mirkogrcic.Data;
import com.mirkogrcic.FormFiller.Data.JOPPD1DataImpl;
import com.mirkogrcic.FormFiller.Data.JOPPD2DataImpl;
import com.mirkogrcic.FormFiller.Data.JOPPD3DataImpl;
import com.mirkogrcic.FormFiller.FormFiller;
import com.mirkogrcic.FormFiller.FormFillerBase;
import com.mirkogrcic.FormFiller.JOPPD1;
import com.mirkogrcic.FormFiller.JOPPD2;
import com.mirkogrcic.FormFiller.JOPPD3;
import com.mirkogrcic.Locales.Locales;
import com.mirkogrcic.Locales.LocalizedText;
import com.mirkogrcic.Locales.LocalizedText.LocaleChangeListener;
import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.Calculator.Result;
import com.mirkogrcic.calculator.TaxValues;
import com.mirkogrcic.calculator.TaxValuesImpl;
import com.mirkogrcic.utils.Util;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataWindow extends JFrame {

    private final static Logger logger = LoggerFactory.getLogger(DataWindow.class.getName());
    private final static SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");

    private Data config;
    private ResultWindow resultWindow;
    private LocalizedText localizedText;
    private DataEditPanel inputPanel;


    public DataWindow(Data config){
        this.config = config;
        localizedText = LocalizedText.getInstance();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(localizedText.format("JOPPDFormFiller"));

        inputPanel = new DataEditPanel(config);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Language selector
        List<Locales.LocaleInfo> locales = Locales.getLocales();
        String[] langStrings = Locales.getTitles().toArray(new String[0]);
        JComboBox<String> langList = new JComboBox<>(langStrings);
        langList.setSelectedIndex(
                this.getLocale().getISO3Language().equals("eng") ? 1 : 0
        );
        langList.setActionCommand("ChangeLanguage");
        langList.addActionListener(new ButtonClickListener());
        mainPanel.add(langList);

        mainPanel.add(inputPanel);

        // Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton calculateButton = new JButton(localizedText.format("CalculateBtn"));
        calculateButton.setActionCommand("Calculate");
        calculateButton.addActionListener(new ButtonClickListener());

        JButton generateImagesButton = new JButton(localizedText.format("GenerateImagesBtn"));
        generateImagesButton.setActionCommand("GenerateImages");
        generateImagesButton.addActionListener(new ButtonClickListener());

        JButton loadButton = new JButton(localizedText.format("LoadBtn"));
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new ButtonClickListener());

        JButton saveButton = new JButton(localizedText.format("SaveBtn"));
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new ButtonClickListener());

        buttonsPanel.add(calculateButton);
        buttonsPanel.add(generateImagesButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);

        mainPanel.add(buttonsPanel);

        this.getContentPane().add(mainPanel);
        this.pack();

        this.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                if( propertyChangeEvent.getPropertyName().equals("locale") ){
                    localizedText.updateLocale();
                    calculateButton.setText(localizedText.format("CalculateBtn"));
                    logger.info("Locale changed");
                }
            }
        });

        localizedText.addLocaleChangeListener(new LocaleChangeListener() {
            @Override
            public void localeChanged(Locale oldLocale, Locale newLocale) {
                calculateButton.setText(localizedText.format("CalculateBtn"));
                generateImagesButton.setText(localizedText.format("GenerateImagesBtn"));
                loadButton.setText(localizedText.format("LoadBtn"));
                saveButton.setText(localizedText.format("SaveBtn"));
            }
        });

    }

    private class ButtonClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();

            TaxValues taxValues = TaxValuesImpl.fromHumanReadable(config.getPension1(), config.getPension2(), config.getTax(), config.getSurtax());
            Calculator calc = new Calculator(config.getGrossIncome(), taxValues);
            Result result = calc.calculate();

            switch (command){
                case "ChangeLanguage": {
                    if (actionEvent.getSource() instanceof JComboBox) {
                        JComboBox<String> comboBox = (JComboBox<String>) actionEvent.getSource();
                        String selectedValue = (String) comboBox.getSelectedItem();
                        Locale newLocale = DataWindow.this.getLocale();
                        logger.debug("Selected locale: {}", selectedValue);
                        switch (selectedValue) {
                            case "English US":
                                newLocale = new Locale("en", "US");
                                break;
                            case "Hrvatski":
                                newLocale = new Locale("hr", "HR");
                                break;
                        }
                        Locale.setDefault(newLocale);
                        DataWindow.this.localizedText.updateLocale();
                        logger.info("Updated locale: {}", newLocale);
                    }
                    break;
                }

                case "Calculate": {
                    resultWindow = new ResultWindow(config, result, DataWindow.this);
                    resultWindow.setLocationRelativeTo(DataWindow.this);
                    resultWindow.setVisible(true);
                    break;
                }

                case "Load": {
                    String filePath;

                    filePath = Util.showFileOpenDialog(DataWindow.this);
                    if (filePath == null) {
                        logger.debug("Cancelled");
                        return;
                    }
                    logger.info(filePath);
                    try {
                        config.load(filePath);
                        inputPanel.updateFromConfig();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Util.showMessageBox(DataWindow.this, "File not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Util.showMessageBox(DataWindow.this, "Error reading file");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Util.showMessageBox(DataWindow.this, "Error parsing some properties in file");
                    }
                    break;
                }

                case "Save": {
                    String filePath;

                    filePath = Util.showFileSaveDialog(DataWindow.this);
                    if (filePath == null) {
                        logger.debug("Cancelled");
                        return;
                    }
                    logger.info(filePath);
                    try {
                        new File(filePath).createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        config.save(filePath);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Util.showMessageBox(DataWindow.this, "File not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Util.showMessageBox(DataWindow.this, "Error reading file");
                    }
                    break;
                }

                case "GenerateImages": {
                    FormFiller[] instances = new FormFillerBase[]{
                            new JOPPD1(new JOPPD1DataImpl(config, result)),
                            new JOPPD2(new JOPPD2DataImpl()),
                            new JOPPD3(new JOPPD3DataImpl(config, result)),
                    };
                    String[] fileNames = {"JOPPD1.png", "JOPPD2.png", "JOPPD3.png"};
                    String folderName;

                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    //chooser.setAcceptAllFileFilterUsed(false);
                    if (chooser.showSaveDialog(DataWindow.this) == JFileChooser.APPROVE_OPTION) {
                        logger.info("getSelectedFile() : " + chooser.getSelectedFile());
                        folderName = chooser.getSelectedFile().getAbsolutePath();
                    } else {
                        logger.info("No folder selected");
                        return;
                    }


                    // Save Images
                    String filePath;

                    for (int i = 0; i < instances.length; ++i) {
                        FormFiller instance = instances[i];
                        filePath = Paths.get(folderName, fileNames[i]).toString();
                        try {
                            ImageIO.write((RenderedImage) instance.getImage(), "png", new File(filePath));
                        } catch (Exception e) {
                            e.printStackTrace();
                            Util.showMessageBox(DataWindow.this, localizedText.format("ErrorSavingImages"));
                            return;
                        }

                        //ImageWindow window = new ImageWindow(instance.getImage());
                        //window.setVisible(true);
                        //imageWindows[i] = window;
                    }
                    Util.showMessageBox(DataWindow.this, localizedText.format("ImagesSuccessfullySaved"));
                    logger.info("Saving images done");

                    // Show windows
                    /*ImageWindow[] imageWindows = new ImageWindow[instances.length];
                    for( int i = 0; i < instances.length; ++i ){
                        FormFiller instance = instances[i];

                        ImageWindow window = new ImageWindow(instance.getImage());
                        window.setVisible(true);
                        imageWindows[i] = window;
                    }*/

                    break;
                }
            }
        }
    }
}

