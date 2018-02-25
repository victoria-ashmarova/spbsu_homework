import ashmarova.task_2_2_1.*;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class TranslatorTest {
    @Test
    public void translationToPostfixFormOneOperationTest() throws IncorrectExpressionException {
        Scanner scan = new Scanner("1 + 4");
        String expectedResult = "1 4 +";
        assertTrue(expectedResult.equals(Translator.getPostFixExpression(scan)));
    }

    @Test
    public void translationToPostfixFormSumAndMultiplicationTest() throws IncorrectExpressionException {
        Scanner scan = new Scanner("1 + 4 * 13");
        String expectedResult = "1 4 13 * +";
        assertTrue(expectedResult.equals(Translator.getPostFixExpression(scan)));
    }

    @Test
    public void translationToPostfixFormWithBracketsTest() throws IncorrectExpressionException {
        Scanner scan = new Scanner("( 1 + 4 ) * 13");
        String expectedResult = "1 4 + 13 *";
        assertTrue(expectedResult.equals(Translator.getPostFixExpression(scan)));
    }

    @Test (expected = IncorrectExpressionException.class)
    public void incorrectExpressionTest() throws IncorrectExpressionException {
        Scanner scan = new Scanner("( 1 + 4  * 13");
        Translator.getPostFixExpression(scan);
    }
}