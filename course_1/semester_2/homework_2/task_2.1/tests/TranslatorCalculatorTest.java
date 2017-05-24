import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TranslatorCalculatorTest {
    /**
     * checks correct finding of expression in infix form
     * @throws IncorrectExpressionException in translator and calculator
     */
    @Test
    public void findValueOfExpressionTest() throws IncorrectExpressionException {
        Scanner scan = new Scanner("14 / ( 3 + 4 )");
        int expectedValue = 2;
        assertEquals(expectedValue, Calculator.findValueOfExpressionInPostfixForm(Translator.getPostFixExpression(scan)));
    }
}
