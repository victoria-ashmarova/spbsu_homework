import org.junit.Test;

import static org.junit.Assert.*;

public class ReaderTest {
    @Test
    public void checkStringToArrayTest(){
        Reader reader = new Reader();
        String expression = "11 + (145 / 5 + 3 * 10) - 62";
        Object expected[] = {11, '+', '(', 145, '/', 5, '+', 3, '*', 10, ')', '-', 62};
        Object fact[] = reader.toArray(expression);
        assertArrayEquals(expected, fact);
    }
}