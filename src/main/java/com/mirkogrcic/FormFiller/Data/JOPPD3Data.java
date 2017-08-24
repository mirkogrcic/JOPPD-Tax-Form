package com.mirkogrcic.FormFiller.Data;

public interface JOPPD3Data {
    String getDate();
    String getDateMark();
    String getSubmitterFullName();
    String getSubmitterOIB();
    String getCityCodeResidence();
    String getCityCodeWork();

    String getGrossIncome();

    String getPension1();
    String getPension2();
    String getPension();
    String getGrossPensionSub();

    String getTax();
    String getSurtax();

    String getNetIncome();
}
