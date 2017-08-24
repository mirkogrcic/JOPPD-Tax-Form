package com.mirkogrcic.FormFiller.Data;

import com.mirkogrcic.Config;
import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.Calculator.Result;
import com.mirkogrcic.utils.Util;

public class JOPPD1DataImpl implements JOPPD1Data {
    private Config config;
    private Result result;

    public JOPPD1DataImpl(Config config, Calculator calculator) {
        this(config, calculator.getResult());
    }

    public JOPPD1DataImpl(Config config, Result result) {
        this.config = config;
        this.result = result;
    }

    @Override
    public String getDate() {
        return Util.formatDateHR(config.getDate());
    }

    @Override
    public String getReportMark() {
        return Util.getMark(config.getDate());
    }

    @Override
    public String getSubmitterOIB() {
        return config.getOib();
    }

    @Override
    public String getSubmitterFullName() {
        return config.getFullName();
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
    public String getSubmitterAddress() {
        return config.getAddress();
    }

    @Override
    public String getSubmitterEmail() {
        return config.getEmail();
    }

    @Override
    public String getTaxSurtaxSum() {
        return Util.formatDoubleHR(result.getTaxSurtaxSum());
    }
}
