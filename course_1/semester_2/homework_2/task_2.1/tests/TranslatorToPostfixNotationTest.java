import org.junit.Test;

import static org.junit.Assert.*;

public class TranslatorToPostfixNotationTest {
    /**
     * checks order in expression in postfix form with operation of the second type
     */
    @Test
    public void translateToPostfixSecondOperationTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {1, '+' , 2};
        Object[] expected = {1, 2, '+'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * checks order in expression in postfix form with operation of the first type
     */
    @Test
    public void translateToPostfixFirstOperationTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {1, '*' , 2};
        Object[] expected = {1, 2, '*'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with operation of the first and the second types
     */
    @Test
    public void translateToPostfixOperationsFirstSecondTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {1, '*' , 2, '-', 4};
        Object[] expected = {1, 2, '*', 4, '-'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with operation of the second and the first types
     */
    @Test
    public void translateToPostfixOperationsSecondFirstTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {1, '+' , 8, '/', 4};
        Object[] expected = {1, 8, 4, '/', '+'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with only operations of the first type
     */
    @Test
    public void translateToPostfixOperationsFirstFirstTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {1, '*' , 8, '/', 4};
        Object[] expected = {1, 8, '*' ,4, '/'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with only operations of the second type
     */
    @Test
    public void translateToPostfixOperationsSecondSecondTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {1, '+' , 8, '-', 4};
        Object[] expected = {1, 8, '+', 4, '-'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with brackets in the end
     */
    @Test
    public void translateToPostfixWithBracketsInTheEndTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {3, '+', '(', 4, '-', 2, ')'};
        Object[] expected = {3, 4, 2, '-', '+'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with brackets in the begin
     */
    @Test
    public void translateToPostfixWithBracketsInTheBeginTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {'(', 16, '-', 8, ')', '*', 5};
        Object[] expected = {16, 8, '-', 5, '*'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }

    /**
     * Checks order in expression in postfix form with a some amount of operations
     */
    @Test
    public void translateToPostfixSomeBigExpressionTest(){
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        Object[] expression = {14, '*', '(', 2, '+', 15, '*', 3, '-', 11, ')', '-', 28, '/', 7};
        Object[] expected = {14, 2, 15, 3, '*', '+', 11, '-', '*', 28, 7, '/', '-'};
        Object[] postfixExpression = translator.translateToPostfixNotation(expression);
        assertArrayEquals(expected, postfixExpression);
    }
}