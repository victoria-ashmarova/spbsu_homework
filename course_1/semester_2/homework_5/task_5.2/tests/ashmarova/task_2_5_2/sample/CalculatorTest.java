package ashmarova.task_2_5_2.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    /**
     * checks correct calculation of calculator without action
     */
    @Test
    public void calculatorsFirstValueTest() {
        Calculator calc = new Calculator();
        assertEquals(14, calc.calculate("14"));
    }

    /**
     * checks correct calculation if last action of calculator is sum
     */
    @Test
    public void calculatorsSumTest() {
        Calculator cacl = new Calculator();
        cacl.calculate("14");
        cacl.setCurrentAction('+');
        assertEquals(29, cacl.calculate("15"));
    }

    /**
     * checks correct calculation if last action of calculator is difference
     */
    @Test
    public void calculatorsDifferenceTest() {
        Calculator cacl = new Calculator();
        cacl.calculate("14");
        cacl.setCurrentAction('-');
        assertEquals(6, cacl.calculate("8"));
    }

    /**
     * checks correct calculation if last action of calculator is quotient
     */
    @Test
    public void calculatorsQuotientTest() {
        Calculator cacl = new Calculator();
        cacl.calculate("54");
        cacl.setCurrentAction('/');
        assertEquals(9, cacl.calculate("6"));
    }

    /**
     * checks correct calculation if last action of calculator is multiplication
     */
    @Test
    public void calculatorsMultiplicationTest() {
        Calculator cacl = new Calculator();
        cacl.calculate("7");
        cacl.setCurrentAction('*');
        assertEquals(42, cacl.calculate("6"));
    }
}