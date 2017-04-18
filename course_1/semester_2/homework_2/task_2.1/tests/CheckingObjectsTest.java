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

    /**
     * checks true define of operation composition
     */
    @Test
    public void checkFirstOperationCompositionTest(){
        CheckingObjects checking = new CheckingObjects();
        assertTrue(checking.isFirstOperation('*'));
    }

    /**
     * checks true define of operation quotient
     */
    @Test
    public void checkFirstOperationQuotientTest(){
        CheckingObjects checking = new CheckingObjects();
        assertTrue(checking.isFirstOperation('/'));
    }

    /**
     * checks that random element is not define as the first operation
     */
    @Test
    public void checkNotFirstOperationTest(){
        CheckingObjects checking = new CheckingObjects();
        assertFalse(checking.isFirstOperation('+'));
    }

    /**
     * checks work with incorrect data
     */
    @Test
    public void checkNotCorrectFirstOperationTest(){
        CheckingObjects checking = new CheckingObjects();
        try {
            assertFalse(checking.isFirstOperation(1));
        } catch (ClassCastException e){}
    }

    /**
     * checks true define of operation sum
     */
    @Test
    public void checkSecondOperationAmountTest(){
        CheckingObjects checking = new CheckingObjects();
        assertTrue(checking.isSecondOperation('+'));
    }

    /**
     * checks true define of operation difference
     */
    @Test
    public void checkSecondOperationDifferenceTest(){
        CheckingObjects checking = new CheckingObjects();
        assertTrue(checking.isSecondOperation('-'));
    }

    /**
     * checks that random element is not define as the second operation
     */
    @Test
    public void checkNotSecondOperationTest(){
        CheckingObjects checking = new CheckingObjects();
        assertFalse(checking.isSecondOperation('*'));
    }

    /**
     * checks work with incorrect data
     */
    @Test
    public void checkNotCorrectSecondOperationTest(){
        CheckingObjects checking = new CheckingObjects();
        try {
            assertFalse(checking.isSecondOperation(1));
        } catch (ClassCastException e){}
    }
}