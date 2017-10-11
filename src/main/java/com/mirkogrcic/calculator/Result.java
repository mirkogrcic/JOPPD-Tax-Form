package com.mirkogrcic.calculator;

import java.math.BigDecimal;

public interface Result {
    BigDecimal getPension1();

    BigDecimal getPension2();

    BigDecimal getPension();

    BigDecimal getGrossPensionSub();

    BigDecimal getTax();

    BigDecimal getSurtax();

    BigDecimal getTaxSurtaxSum();

    BigDecimal getTotalSum();

    BigDecimal getNetIncome();
}
