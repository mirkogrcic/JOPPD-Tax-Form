package com.mirkogrcic.calculator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void test1() {
        TaxValuesImpl taxes = new TaxValuesImpl();
        Calculator calc = new Calculator(BigDecimal.valueOf(0L), taxes);
        Calculator.Result result;
        taxes.loadHumanReadable(
                new BigDecimal("7.5"),
                new BigDecimal("2.5"),
                new BigDecimal("24"),
                new BigDecimal("15")
            );
        calc.setGrossIncome(new BigDecimal("5000"));
        result = calc.calculate();
        assertTrue(new BigDecimal("375.00").compareTo(result.getPension1()) == 0);
        assertTrue(new BigDecimal("125.00").compareTo(result.getPension2()) == 0);
        assertTrue(new BigDecimal("500.00").compareTo(result.getPension()) == 0);
        assertTrue(new BigDecimal("4500.00").compareTo(result.getGrossPensionSub()) == 0);
        assertTrue(new BigDecimal("1080.00").compareTo(result.getTax()) == 0);
        assertTrue(new BigDecimal("162.00").compareTo(result.getSurtax()) == 0);
        assertTrue(new BigDecimal("1242.00").compareTo(result.getTaxSurtaxSum()) == 0);
        assertTrue(new BigDecimal("1742.00").compareTo(result.getTotalSum()) == 0);
        assertTrue(new BigDecimal("3258.00").compareTo(result.getNetIncome()) == 0);
    }
}