package com.mirkogrcic.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    /*@org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }*/

    private Date getDate(int year, int month, int day){
        return Util.getDate(year, month, day);
    }

    @Test
    public void getMark() {
        Date date;
        
        date = getDate(2017, 1, 1);
        assertEquals("17001", Util.getMark(date));

        date = getDate(2017, 2, 14);
        assertEquals("17045", Util.getMark(date));

        date = getDate(2017, 2, 20);
        assertEquals("17051", Util.getMark(date));

        date = getDate(2017, 8, 14);
        assertEquals("17226", Util.getMark(date));

        date = getDate(2018, 8, 14);
        assertEquals("18226", Util.getMark(date));
    }

    @Test
    public void roundDouble() {
        assertEquals(34.567d,       34.5666547d,        3);
        assertEquals(876.86533d,    876.8653254325d,    5);
    }

    @Test
    public void formatDoubleHR(){
        assertEquals("25.473,30", Util.formatDoubleHR(25473.3d));
        assertEquals("250.473,30", Util.formatDoubleHR(250473.3d));
        assertEquals("473,36", Util.formatDoubleHR(473.356d));
        assertEquals("1.473,35", Util.formatDoubleHR(1473.354d));
    }

    @Test
    public void formatDateHR() throws Exception {
        Date date;

        date = getDate(2017, 1, 1);
        assertEquals("01.01.2017.", Util.formatDateHR(date));

        date = getDate(2010, 12, 31);
        assertEquals("31.12.2010.", Util.formatDateHR(date));

        date = getDate(2030, 8, 13);
        assertEquals("13.08.2030.", Util.formatDateHR(date));
    }
}