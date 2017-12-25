import org.junit.Test;
import sample.Calculator;
import static org.junit.Assert.assertEquals;

/**
 * Class contains tests for calculator.
 */
public class CalculatorTest{
    private final double DELTA = 0.0001;

    /**
     * checks if sum is correct
     */
    @Test
    public void sumTest() {
        assertEquals(9, Calculator.calculate(6, 3, '+'), DELTA);
    }

    /**
     * checks if difference is correct
     */
    @Test
    public void differenceTest() {
        assertEquals(3, Calculator.calculate(6, 3, '-'), DELTA);
    }

    /**
     * checks if multiplication is correct
     */
    @Test
    public void multiplicationTest() {
        assertEquals(18, Calculator.calculate(6, 3, '*'), DELTA);
    }

    /**
     * checks if quotient is correct
     */
    @Test
    public void quotientTest() {
        assertEquals(2, Calculator.calculate(6, 3, '/'), DELTA);
    }

    /**
     * checks if division by zero s not available
     */
    @Test (expected = ArithmeticException.class)
    public void divisionByZeroTest() {
        assertEquals(3, Calculator.calculate(6, 0, '/'), DELTA);
    }
}
