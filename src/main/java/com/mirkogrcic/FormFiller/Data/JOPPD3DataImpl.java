package com.mirkogrcic.FormFiller.Data;

import com.mirkogrcic.Data;
import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.Calculator.Result;
import com.mirkogrcic.utils.Util;

public class JOPPD3DataImpl implements JOPPD3Data {
    private Data data;
    private Result result;

    public JOPPD3DataImpl(Data data, Calculator calculator) {
        this(data, calculator.getResult());
    }

    public JOPPD3DataImpl(Data data, Result result) {
        this.data = data;
        this.result = result;
    }

    @Override
    public String getDate() {
        return Util.formatDateHR(data.getDate());
    }

    @Override
    public String getDateMark() {
        return Util.getMark(data.getDate());
    }

    @Override
    public String getSubmitterOIB() {
        return data.getOib();
    }

    @Override
    public String getCityCodeResidence() {
        return data.getCityCodeResidence();
    }

    @Override
    public String getCityCodeWork() {
        return data.getCityCodeWork();
    }

    @Override
    public String getSubmitterFullName() {
        return data.getFullName();
    }

    @Override
    public String getGrossIncome() {
        return Util.formatDoubleHR(data.getGrossIncome());
    }

    @Override
    public String getPension1() {
        return Util.formatDoubleHR(result.getPension1());
    }

    @Override
    public String getPension2() {
        return Util.formatDoubleHR(result.getPension2());
    }

    @Override
    public String getPension() {
        return Util.formatDoubleHR(result.getPension());
    }

    @Override
    public String getGrossPensionSub() {
        return Util.formatDoubleHR(result.getGrossPensionSub());
    }

    @Override
    public String getTax() {
        return Util.formatDoubleHR(result.getTax());
    }

    @Override
    public String getSurtax() {
        return Util.formatDoubleHR(result.getSurtax());
    }

    @Override
    public String getNetIncome() {
        return Util.formatDoubleHR(result.getNetIncome());
    }
}
