package com.mirkogrcic.calculator;


public class Calculator {
    private Double grossIncome;  // Bruto plaća

    private TaxValues taxValues;
    private Result result;


    public Calculator(){
        this(0d, new TaxValues());
    }

    public Calculator(double grossIncome, TaxValues taxValues){
        this.grossIncome = grossIncome;
        this.taxValues = taxValues;
    }


    public Double getGrossIncome(){
        return this.grossIncome;
    }

    public void setGrossIncome(Double value){
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
        this.result = new Result();

        this.result.pension1 = this.grossIncome * this.taxValues.pension1;
        this.result.pension2 = this.grossIncome * this.taxValues.pension2;
        this.result.pension = this.result.pension1 + this.result.pension2;
        this.result.grossPensionSub = this.grossIncome - this.result.pension;

        this.result.tax = this.result.grossPensionSub * this.taxValues.tax;
        this.result.surtax = this.result.tax * this.taxValues.surtax;
        this.result.taxSurtaxSum = this.result.tax + this.result.surtax;

        this.result.totalSum = this.result.pension + this.result.taxSurtaxSum;
        this.result.netIncome = this.grossIncome - this.result.totalSum;

        return this.result;
    }


    public class Result implements com.mirkogrcic.calculator.Result {
        private Double pension1;
        private Double pension2;
        private Double pension;
        private Double grossPensionSub;  // gross - pension

        private Double tax;
        private Double surtax;
        private Double taxSurtaxSum;

        private Double totalSum;
        private Double netIncome;

        public Result(){

        }

        @Override
        public Double getPension1() {
            return pension1;
        }

        @Override
        public Double getPension2() {
            return pension2;
        }

        @Override
        public Double getPension() {
            return pension;
        }

        @Override
        public Double getGrossPensionSub() {
            return grossPensionSub;
        }

        @Override
        public Double getTax() {
            return tax;
        }

        @Override
        public Double getSurtax() {
            return surtax;
        }

        @Override
        public Double getTaxSurtaxSum() {
            return taxSurtaxSum;
        }

        @Override
        public Double getTotalSum() {
            return totalSum;
        }

        @Override
        public Double getNetIncome() {
            return netIncome;
        }

        @Override
        public boolean equals(Object o) {
            if( !( o instanceof Result) )
                throw new IllegalArgumentException(String.format("Object must be of type '%s'", Result.class.getName()));
            return super.equals(o);
        }
    }

}
