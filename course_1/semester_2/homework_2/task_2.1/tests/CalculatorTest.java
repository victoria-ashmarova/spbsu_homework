import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void calculatorSumTest() throws EmptyStackException {
        Calculator calculator = new Calculator();
        StackWithPointers stack = new StackWithPointers();
        stack.push(3);
        stack.push(10);
        Object operation = '+';
        assertEquals(13, calculator.getValueOfOperation(stack, operation));
    }

    @Test
    public void calculatorDifferenceTest() throws EmptyStackException {
        Calculator calculator = new Calculator();
        StackWithPointers stack = new StackWithPointers();
        stack.push(10);
        stack.push(3);
        Object operation = '-';
        assertEquals(7, calculator.getValueOfOperation(stack, operation));
    }

    @Test
    public void calculatorCompositionTest() throws EmptyStackException {
        Calculator calculator = new Calculator();
        StackWithPointers stack = new StackWithPointers();
        stack.push(10);
        stack.push(3);
        Object operation = '*';
        assertEquals(30, calculator.getValueOfOperation(stack, operation));
    }

    @Test
    public void calculatorQuotientTest() throws EmptyStackException {
        Calculator calculator = new Calculator();
        StackWithPointers stack = new StackWithPointers();
        stack.push(30);
        stack.push(3);
        Object operation = '/';
        assertEquals(10, calculator.getValueOfOperation(stack, operation));
    }

    @Test
    public void calculatorTest() throws EmptyStackException {
        Calculator calculator = new Calculator();
        Object expression[] = {2, 3, '*', 4, '+'};
        assertEquals(10, calculator.findValueOfExpressionInPostfixForm(expression));
    }
}