import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class TranslatorTest {
    @Test
    public void translationToPostfixFormOneOperationTest(){
        try {
            Scanner scan = new Scanner("1 + 4");
            String expectedResult = "1 4 +";
            assertTrue(expectedResult.equals(Translator.getPostFixExpression(scan)));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void translationToPostfixFormSumAndMultiplicationTest(){
        try {
            Scanner scan = new Scanner("1 + 4 * 13");
            String expectedResult = "1 4 13 * +";
            assertTrue(expectedResult.equals(Translator.getPostFixExpression(scan)));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }

    @Test
    public void translationToPostfixFormWithBracketsTest(){
        try {
            Scanner scan = new Scanner("( 1 + 4 ) * 13");
            String expectedResult = "1 4 + 13 *";
            assertTrue(expectedResult.equals(Translator.getPostFixExpression(scan)));
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }
}