package com.mirkogrcic.gui;

import com.mirkogrcic.calculator.Calculator;
import com.mirkogrcic.calculator.Result;
import com.mirkogrcic.utils.Util;

public class ResultStringAdapter {
    private Calculator.Result result;

    public ResultStringAdapter(Calculator.Result result) {
        this.result = result;
    }


    public String getPension1() {
        return Util.formatDoubleHR(result.getPension1());
    }

    public String getPension2() {
        return Util.formatDoubleHR(result.getPension2());
    }

    public String getPension() {
        return Util.formatDoubleHR(result.getPension());
    }

    public String getGrossPensionSub() {
        return Util.formatDoubleHR(result.getGrossPensionSub());
    }

    public String getTax() {
        return Util.formatDoubleHR(result.getTax());
    }

    public String getSurtax() {
        return Util.formatDoubleHR(result.getSurtax());
    }

    public String getTaxSurtaxSum() {
        return Util.formatDoubleHR(result.getTaxSurtaxSum());
    }

    public String getTotalSum() {
        return Util.formatDoubleHR(result.getTotalSum());
    }

    public String getNetIncome() {
        return Util.formatDoubleHR(result.getNetIncome());
    }
}
