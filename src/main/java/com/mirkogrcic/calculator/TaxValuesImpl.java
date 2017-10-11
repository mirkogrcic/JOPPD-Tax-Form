package com.mirkogrcic.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxValuesImpl implements TaxValues {
    private BigDecimal pension1;
    private BigDecimal pension2;
    private BigDecimal tax;
    private BigDecimal surtax;

    public TaxValuesImpl(){
        this(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public TaxValuesImpl(BigDecimal pension1, BigDecimal pension2, BigDecimal tax, BigDecimal surtax){
        if( pension1.compareTo(BigDecimal.ONE) > 0
                || pension2.compareTo(BigDecimal.ONE) > 0
                || tax.compareTo(BigDecimal.ONE) > 0
                || surtax.compareTo(BigDecimal.ONE) > 0

                || pension1.compareTo(BigDecimal.ZERO) < 0
                || pension2.compareTo(BigDecimal.ZERO) < 0
                || tax.compareTo(BigDecimal.ZERO) < 0
                || surtax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value range must be 0d-1d or use TaxValues.fromHumanReadable for 0-100");
        }
        this.pension1 = pension1;
        this.pension2 = pension2;
        this.tax = tax;
        this.surtax = surtax;
    }

    public static TaxValues fromHumanReadable(BigDecimal pension1, BigDecimal pension2, BigDecimal tax, BigDecimal surtax){
        TaxValuesImpl taxValues = new TaxValuesImpl();
        taxValues.loadHumanReadable(pension1, pension2, tax, surtax);
        return taxValues;
    }

    public void loadHumanReadable(BigDecimal pension1, BigDecimal pension2, BigDecimal tax, BigDecimal surtax){
        BigDecimal hundred = BigDecimal.valueOf(100d);
        this.pension1 = pension1.divide(hundred, 0, RoundingMode.HALF_UP);
        this.pension2 = pension2.divide(hundred, 0, RoundingMode.HALF_UP);
        this.tax = tax.divide(hundred, 0, RoundingMode.HALF_UP);
        this.surtax = surtax.divide(hundred, 0, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getPension1() {
        return pension1;
    }

    @Override
    public void setPension1(BigDecimal pension1) {
        this.pension1 = pension1;
    }

    @Override
    public BigDecimal getPension2() {
        return pension2;
    }

    @Override
    public void setPension2(BigDecimal pension2) {
        this.pension2 = pension2;
    }

    @Override
    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public BigDecimal getSurtax() {
        return surtax;
    }

    @Override
    public void setSurtax(BigDecimal surtax) {
        this.surtax = surtax;
    }
}
