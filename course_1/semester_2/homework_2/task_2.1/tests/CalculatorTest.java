import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void findValueOfExpressionWithSumTest(){
        try {
            String toGetValue = "23 14 +";
            assertEquals(37, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void findValueOfExpressionWithDifferenceTest(){
        try {
            String toGetValue = "23 14 -";
            assertEquals(9, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void findValueOfExpressionWithMultiplicationTest(){
        try {
            String toGetValue = "23 14 *";
            assertEquals(322, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void findValueOfExpressionWithQuotientTest(){
        try {
            String toGetValue = "28 14 /";
            assertEquals(2, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void findValueOfExpressionWithDivisionToZeroTest(){
        try {
            String toGetValue = "28 0 /";
            Calculator.findValueOfExpressionInPostfixForm(toGetValue);
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void findValueOfExpressionTest(){
        try{
            String toGetValue = "15 4 + 5 - 45 15 / *";
            assertEquals(42, Calculator.findValueOfExpressionInPostfixForm(toGetValue));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }
}