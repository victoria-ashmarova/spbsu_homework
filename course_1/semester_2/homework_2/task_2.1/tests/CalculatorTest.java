import ashmarova.task_2_2_1.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    /**
     * checks corrects sum
     * @throws IncorrectExpressionException in calculator
     */
    @Test
    public void findValueOfExpressionWithSumTest() throws IncorrectExpressionException {
        String toGetValue = "23 14 +";
        assertEquals(37, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
    }

    /**
     * checks correct difference
     * @throws IncorrectExpressionException in calculator
     */
    @Test
    public void findValueOfExpressionWithDifferenceTest() throws IncorrectExpressionException {
        String toGetValue = "23 14 -";
        assertEquals(9, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
    }

    /**
     * checks correct multiplication
     * @throws IncorrectExpressionException in calculator
     */
    @Test
    public void findValueOfExpressionWithMultiplicationTest() throws IncorrectExpressionException {
        String toGetValue = "23 14 *";
        assertEquals(322, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
    }

    /**
     * checks correct quotient
     * @throws IncorrectExpressionException in calculator
     */
    @Test
    public void findValueOfExpressionWithQuotientTest() throws IncorrectExpressionException {
        String toGetValue = "28 14 /";
        assertEquals(2, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
    }

    /**
     * checks correct division to zero
     * @throws IncorrectExpressionException in calculator, when there is division to zero
     */
    @Test (expected = IncorrectExpressionException.class)
    public void findValueOfExpressionWithDivisionToZeroTest() throws IncorrectExpressionException {
        String toGetValue = "28 0 /";
        Calculator.findValueOfExpressionInPostfixForm(toGetValue);
    }

    /**
     * checks correct finding value
     * @throws IncorrectExpressionException in calculator
     */
    @Test
    public void findValueOfExpressionTest() throws IncorrectExpressionException {
        String toGetValue = "15 4 + 5 - 45 15 / *";
        assertEquals(42, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
    }
}