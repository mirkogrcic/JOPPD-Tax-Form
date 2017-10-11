package com.mirkogrcic.FormFiller.Data;

import com.mirkogrcic.Data;
import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.Calculator.Result;
import com.mirkogrcic.utils.Util;

public class JOPPD1DataImpl implements JOPPD1Data {
    private Data data;
    private Result result;

    public JOPPD1DataImpl(Data data, Calculator calculator) {
        this(data, calculator.getResult());
    }

    public JOPPD1DataImpl(Data data, Result result) {
        this.data = data;
        this.result = result;
    }

    @Override
    public String getDate() {
        return Util.formatDateHR(data.getDate());
    }

    @Override
    public String getReportMark() {
        return Util.getMark(data.getDate());
    }

    @Override
    public String getSubmitterOIB() {
        return data.getOib();
    }

    @Override
    public String getSubmitterFullName() {
        return data.getFullName();
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
        return data.getAddress();
    }

    @Override
    public String getSubmitterEmail() {
        return data.getEmail();
    }

    @Override
    public String getTaxSurtaxSum() {
        return Util.formatDoubleHR(result.getTaxSurtaxSum());
    }
}
