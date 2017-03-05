package bondCalculator;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @Time: Feb 17, 2017
 * @Author: zen
 */


public class CalculatorTest {
    final double DIFFERENCE = 0.0000001;
    Calculator calc;
    
    @Before
    public void setup() {
        calc = new Calculator();
    }
    
    @Test
    public void priceCalcBasicTest() {
        assertEquals(832.3922451, calc.CalcPrice(0.10, 5, 1000, 0.15), DIFFERENCE );
    }
    
    @Test
    public void yieldCalcBasicTest() {
        assertEquals(0.1499974, calc.CalcYield(0.10, 5, 1000, 832.4), DIFFERENCE);
    }
    
    @Test
    public void priceCalcSameRateTest() {
        assertEquals(1000.0000000, calc.CalcPrice(0.15, 5, 1000, 0.15), DIFFERENCE);
    }
    
    @Test
    public void yieldCalcSameRateTest() {
        assertEquals(0.1000000, calc.CalcYield(0.10, 5, 1000, 1000), DIFFERENCE);
    }
    
    @Test
    public void priceCalcPremiumTest() {
        assertEquals(1079.8542007, calc.CalcPrice(0.10, 5, 1000, 0.08), DIFFERENCE);
    }
    
    @Test
    public void yieldCalcPremiumTest() {
        assertEquals(0.0800001, calc.CalcYield(0.10, 5, 1000, 1079.8542007), DIFFERENCE);
    }
    
    @Test
    public void priceCalcHundredYearTest() {
        assertEquals(2004.8993937794, calc.CalcPrice(0.13, 100, 1234, 0.08), DIFFERENCE);
    }
    
    @Test
    public void yieldCalcHundredTest() {
        assertEquals(0.08, calc.CalcYield(0.13, 100, 1234, 2004.8993937794), DIFFERENCE);
    }
    
    @Test
    public void CalcConsistentTest() {
        assertEquals(528.8807463, calc.CalcPrice(0.10, 30, 1000, 0.19), DIFFERENCE);
        assertEquals(0.1900000, calc.CalcYield(0.10, 30, 1000, 528.8807463), DIFFERENCE);
    }
    
   

}
