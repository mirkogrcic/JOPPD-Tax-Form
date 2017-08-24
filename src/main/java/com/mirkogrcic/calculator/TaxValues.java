package com.mirkogrcic.calculator;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class TaxValues{
    public Double pension1;
    public Double pension2;
    public Double tax;
    public Double surtax;

    public TaxValues(){
        this(0d, 0d, 0d, 0d);
    }

    public TaxValues(Double pension1, Double pension2, Double tax, Double surtax){
        if( pension1 > 1 || pension2 > 1 || tax > 1 || surtax > 1){
            throw new ValueException("Value range must be 0d-1d or use fromHumanReadable for 0-100");
        }
        this.pension1 = pension1;
        this.pension2 = pension2;
        this.tax = tax;
        this.surtax = surtax;
    }

    public static TaxValues fromHumanReadable(Double pension1, Double pension2, Double tax, Double surtax){
        TaxValues taxValues = new TaxValues();
        taxValues.loadHumanReadable(pension1, pension2, tax, surtax);
        return taxValues;
    }

    public void loadHumanReadable(Double pension1, Double pension2, Double tax, Double surtax){
        this.pension1 = pension1 / 100;
        this.pension2 = pension2 / 100;
        this.tax = tax / 100;
        this.surtax = surtax / 100;

    }
}
