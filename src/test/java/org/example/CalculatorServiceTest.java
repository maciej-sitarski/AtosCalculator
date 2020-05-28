package org.example;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorServiceTest  {

    @Test
    public void testFirstValidationOfCurrencyName() {

        //given
        CalculatorService calculatorService = new CalculatorService();

        //when
        Boolean correctName1 = calculatorService.validationOfCurrencyName("USD");
        Boolean correctName2 = calculatorService.validationOfCurrencyName("PLN");
        Boolean correctName3 = calculatorService.validationOfCurrencyName("123");
        Boolean correctName4 = calculatorService.validationOfCurrencyName("");
        Boolean correctName5 = calculatorService.validationOfCurrencyName(null);

        //then
        Assert.assertEquals(correctName1, true);
        Assert.assertEquals(correctName2, true);
        Assert.assertEquals(correctName3, false);
        Assert.assertEquals(correctName4, false);
        Assert.assertEquals(correctName5, false);
    }

    @Test
    public void testValidationOfAmount() {

        //given
        CalculatorService calculatorService = new CalculatorService();

        //when
        Boolean correctAmount1 = calculatorService.validationOfAmount("1.1.1");
        Boolean correctAmount2 = calculatorService.validationOfAmount("PLN");
        Boolean correctAmount3 = calculatorService.validationOfAmount("123");
        Boolean correctAmount4 = calculatorService.validationOfAmount("");
        Boolean correctAmount5 = calculatorService.validationOfAmount(null);

        //then
        Assert.assertEquals(correctAmount1, false);
        Assert.assertEquals(correctAmount2, false);
        Assert.assertEquals(correctAmount3, true);
        Assert.assertEquals(correctAmount4, false);
        Assert.assertEquals(correctAmount5, false);
    }

    @Test
    public void testCalculateAmount() {

        //given
        CalculatorService calculatorService = new CalculatorService();

        //when
        Double amount1 = calculatorService.calculateAmount("USD", 100.0);
        Double amount2 = calculatorService.calculateAmount("JPY", 1.0);
        Double amount3 = calculatorService.calculateAmount("USD", 0.0);
        Double amount4 = calculatorService.calculateAmount("USD", null);

        //then
        Assert.assertEquals(amount1, new Double(109.74999999999999));
        Assert.assertEquals(amount2, new Double(117.92));
        Assert.assertEquals(amount3, new Double(0.0));
        Assert.assertEquals(amount4, new Double(0.0));

    }
}
