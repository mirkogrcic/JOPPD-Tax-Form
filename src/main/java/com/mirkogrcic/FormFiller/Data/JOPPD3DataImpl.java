package com.mirkogrcic.FormFiller.Data;

import com.mirkogrcic.Config;
import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.Calculator.Result;
import com.mirkogrcic.utils.Util;

public class JOPPD3DataImpl implements JOPPD3Data {
    private Config config;
    private Result result;

    public JOPPD3DataImpl(Config config, Calculator calculator) {
        this(config, calculator.getResult());
    }

    public JOPPD3DataImpl(Config config, Result result) {
        this.config = config;
        this.result = result;
    }

    @Override
    public String getDate() {
        return Util.formatDateHR(config.getDate());
    }

    @Override
    public String getDateMark() {
        return Util.getMark(config.getDate());
    }

    @Override
    public String getSubmitterOIB() {
        return config.getOib();
    }

    @Override
    public String getCityCodeResidence() {
        return config.getCityCodeResidence();
    }

    @Override
    public String getCityCodeWork() {
        return config.getCityCodeWork();
    }

    @Override
    public String getSubmitterFullName() {
        return config.getFullName();
    }

    @Override
    public String getGrossIncome() {
        return Util.formatDoubleHR(config.getGrossIncome());
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
