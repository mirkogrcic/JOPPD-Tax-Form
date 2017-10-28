package com.mirkogrcic.utils;

import java.awt.Component;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Util {
    /**
     * Returns YYDDD
     * YY: 2017 -> 17
     * DDD: Day of the year 2017/01/01 -> 1
     *
     * @param date the date
     * @return YYDDD
     */
    public static String getMark(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Integer year = calendar.get(Calendar.YEAR);
        String yearStr = year.toString().substring(2);
        Integer dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        DecimalFormat formatter = new DecimalFormat("000");

        String dayOfYearStr = formatter.format(dayOfYear);

        return yearStr + dayOfYearStr;
    }

    public static double roundDouble(double value, int decimals){
        double a = Math.pow(10, decimals);
        return Math.round (value * a) / a;
    }

    public static Rectangle getRectangle(int x, int y, int x2, int y2){
        return new Rectangle(x, y, x2 - x, y2 - y);
    }

    public static String formatDoubleHR(double value){
        DecimalFormat decimalFmt = new DecimalFormat("###,###,###,##0.00");
        DecimalFormatSymbols symbols = decimalFmt.getDecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        decimalFmt.setDecimalFormatSymbols(symbols);
        return decimalFmt.format(value);
    }

    public static String formatDoubleHR(BigDecimal value){
        DecimalFormat decimalFmt = new DecimalFormat("###,###,###,##0.00");
        DecimalFormatSymbols symbols = decimalFmt.getDecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        decimalFmt.setDecimalFormatSymbols(symbols);
        return decimalFmt.format(value);
    }

    public static String formatDateHR(Date date){
        SimpleDateFormat dateFmt = new SimpleDateFormat("dd.MM.yyyy.");
        return dateFmt.format(date);
    }

    public static Image getImageResource(String path){
        InputStream inStream = path.getClass().getResourceAsStream(path);
        if( inStream == null )
            return null;

        try {
            return ImageIO.read(inStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDate(int year, int month, int day){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);

        cal.set(year, month-1, day);
        Date date = cal.getTime();
        return date;
    }

    public static String showFileOpenDialog(Component parent){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("Properties file(.ini)", "ini"));
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        else {
            return null;
        }
    }

    public static String showFileSaveDialog(Component parent){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("Properties file(.ini)", "ini"));
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return getSelectedFileWithExtension(chooser);
        }
        else {
            return null;
        }
    }

    /**
     * Returns the selected file from a JFileChooser, including the extension from
     * the file filter.
     */
    public static String getSelectedFileWithExtension(JFileChooser c) {
        File file = c.getSelectedFile();
        String path = file.getAbsolutePath();
        if (c.getFileFilter() instanceof FileNameExtensionFilter) {
            String[] exts = ((FileNameExtensionFilter)c.getFileFilter()).getExtensions();
            String nameLower = c.getSelectedFile().getName().toLowerCase();
            for (String ext : exts) { // check if it already has a valid extension
                if (nameLower.endsWith('.' + ext.toLowerCase())) {
                    return file.getAbsolutePath(); // if yes, return as-is
                }
            }
            // if not, append the first extension from the selected filter
            return path + '.' + exts[0];
        }
        return path;
    }

    public static void showMessageBox(String message){
        showMessageBox(null, message);
    }

    public static void showMessageBox(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message);
    }

    public static NumberFormatException getNumberFormatException(String message, Throwable source){
        NumberFormatException e = new NumberFormatException(message);
        if( source != null )
            e.addSuppressed(source);
        return e;
    }

    public static String capitalizeFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
