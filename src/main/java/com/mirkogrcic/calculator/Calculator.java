package com.mirkogrcic.calculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    private BigDecimal grossIncome;  // Bruto plaća

    private TaxValues taxValues;
    private Result result;

    public Calculator(){

    }

    public Calculator(BigDecimal grossIncome, TaxValues taxValues){
        this.grossIncome = grossIncome;
        this.taxValues = taxValues;
    }


    public BigDecimal getGrossIncome(){
        return this.grossIncome;
    }

    public void setGrossIncome(BigDecimal value){
        this.grossIncome = value;
    }


    public TaxValues getTaxValues() {
        return this.taxValues;
    }

    public void setTaxValues(TaxValues value){
        this.taxValues = value;
    }


    public Result getResult() {
        return this.result;
    }


    public Result calculate(){
        if (this.grossIncome == null)
            throw new IllegalStateException("grossIncome must be specified");

        if (this.taxValues == null)
            throw new IllegalStateException("taxValues must be specified");

        this.result = new Result();
        
        int precision = 2;
        RoundingMode roundingMode = RoundingMode.HALF_UP;

        // Pension
        this.result.pension1 = this.grossIncome.multiply(this.taxValues.getPension1());
        this.result.pension1 = this.result.pension1.setScale(precision, roundingMode);

        this.result.pension2 = this.grossIncome.multiply(this.taxValues.getPension2());
        this.result.pension2 = this.result.pension2.setScale(precision, roundingMode);

        this.result.pension = this.result.pension1.add(this.result.pension2);
        this.result.pension = this.result.pension.setScale(precision, roundingMode);

        this.result.grossPensionSub = this.grossIncome.subtract(this.result.pension);
        this.result.grossPensionSub = this.result.grossPensionSub.setScale(precision, roundingMode);

        // TAX
        this.result.tax = this.result.grossPensionSub.multiply(this.taxValues.getTax());
        this.result.tax = this.result.tax.setScale(precision, roundingMode);

        this.result.surtax = this.result.tax.multiply(this.taxValues.getSurtax());
        this.result.surtax = this.result.surtax.setScale(precision, roundingMode);

        this.result.taxSurtaxSum = this.result.tax.add(this.result.surtax);
        this.result.taxSurtaxSum = this.result.taxSurtaxSum.setScale(precision, roundingMode);

        // TOTALS
        this.result.totalSum = this.result.pension.add(this.result.taxSurtaxSum);
        this.result.totalSum = this.result.totalSum.setScale(precision, roundingMode);

        this.result.netIncome = this.grossIncome.subtract(this.result.totalSum);
        this.result.netIncome = this.result.netIncome.setScale(precision, roundingMode);

        return this.result;
    }


    public class Result implements com.mirkogrcic.calculator.Result {
        private BigDecimal pension1;
        private BigDecimal pension2;
        private BigDecimal pension;
        private BigDecimal grossPensionSub;  // gross - pension

        private BigDecimal tax;
        private BigDecimal surtax;
        private BigDecimal taxSurtaxSum;

        private BigDecimal totalSum;
        private BigDecimal netIncome;

        public Result(){

        }

        @Override
        public BigDecimal getPension1() {
            return pension1;
        }

        @Override
        public BigDecimal getPension2() {
            return pension2;
        }

        @Override
        public BigDecimal getPension() {
            return pension;
        }

        @Override
        public BigDecimal getGrossPensionSub() {
            return grossPensionSub;
        }

        @Override
        public BigDecimal getTax() {
            return tax;
        }

        @Override
        public BigDecimal getSurtax() {
            return surtax;
        }

        @Override
        public BigDecimal getTaxSurtaxSum() {
            return taxSurtaxSum;
        }

        @Override
        public BigDecimal getTotalSum() {
            return totalSum;
        }

        @Override
        public BigDecimal getNetIncome() {
            return netIncome;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;

            if( !( o instanceof Result) )
                return false;

            Result result = (Result) o;

            return result.pension1.equals(pension1)
                    && result.pension2.equals(pension2)
                    && result.pension.equals(pension)
                    && result.grossPensionSub.equals(grossPensionSub)

                    && result.tax.equals(tax)
                    && result.surtax.equals(surtax)
                    && result.taxSurtaxSum.equals(taxSurtaxSum)

                    && result.totalSum.equals(totalSum)
                    && result.netIncome.equals(netIncome);
        }
    }

}
