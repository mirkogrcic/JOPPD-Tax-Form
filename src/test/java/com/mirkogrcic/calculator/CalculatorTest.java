package com.mirkogrcic.calculator;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void test1() {
        TaxValues taxes = new TaxValues();
        Calculator calc = new Calculator(0d, taxes);
        Calculator.Result result;
        taxes.loadHumanReadable(7.5d, 2.5d, 24d, 15d);
        calc.setGrossIncome(5000d);
        result = calc.calculate();
        assertEquals(375.00d,   (double) result.getPension1());
        assertEquals(125.00d,   (double) result.getPension2());
        assertEquals(500.00d,   (double) result.getPension());
        assertEquals(4500.00d,  (double) result.getGrossPensionSub());
        assertEquals(1080.00d,  (double) result.getTax());
        assertEquals(162.00d,   (double) result.getSurtax());
        assertEquals(1242.00d,  (double) result.getTaxSurtaxSum());
        assertEquals(1742.00d,  (double) result.getTotalSum());
        assertEquals(3258.00d,  (double) result.getNetIncome());
    }
}