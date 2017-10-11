package com.mirkogrcic.calculator;

import java.math.BigDecimal;

public interface TaxValues {

    BigDecimal getPension1();

    void setPension1(BigDecimal pension1);

    BigDecimal getPension2();

    void setPension2(BigDecimal pension2);

    BigDecimal getTax();

    void setTax(BigDecimal tax);

    BigDecimal getSurtax();

    void setSurtax(BigDecimal surtax);
}
