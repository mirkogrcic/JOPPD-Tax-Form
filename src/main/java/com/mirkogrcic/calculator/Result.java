package com.mirkogrcic.calculator;

public interface Result {
    Double getPension1();

    Double getPension2();

    Double getPension();

    Double getGrossPensionSub();

    Double getTax();

    Double getSurtax();

    Double getTaxSurtaxSum();

    Double getTotalSum();

    Double getNetIncome();
}
