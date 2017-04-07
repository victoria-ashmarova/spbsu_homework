import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingObjectsTest {
    @Test
    public void checkOperationTest(){
        CheckingObjects checking = new CheckingObjects();
        assertTrue(checking.isOperation('+'));
        assertTrue(checking.isOperation('-'));
        assertTrue(checking.isOperation('*'));
        assertTrue(checking.isOperation('/'));
        assertFalse(checking.isOperation('a'));
        assertFalse(checking.isOperation(3.14));
        assertFalse(checking.isOperation("aaaa"));
    }

    @Test
    public void checkNumber(){
        CheckingObjects checking = new CheckingObjects();
        Object toCheckNumber = 2;
        assertTrue(checking.isNumber(toCheckNumber));
        Object toCheckLitera = 'a';
        assertFalse(checking.isNumber(toCheckLitera));
    }

}