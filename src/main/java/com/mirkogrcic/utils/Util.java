package com.mirkogrcic.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    /**
     * Returns YYDDD
     * YY: 2017 -> 17
     * DDD: Day of the year 2017/01/01 -> 1
     *
     * @param date
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
        chooser.setFileFilter(new FileNameExtensionFilter("Properties file(.properties)", "properties"));
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
        chooser.setFileFilter(new FileNameExtensionFilter("Properties file(.properties)", "properties"));
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        else {
            return null;
        }
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
}
