import org.junit.Test;

import static org.junit.Assert.*;

public class SingleLinkedListTest {
    /**
     * Checks that size of created list is 0
     */
    @Test
    public void creationSizeSingleLinkedListTest(){
        List<Integer> list = new SingleLinkedList<>();
        assertEquals(0, list.getSize());
    }

    /**
     * Checks correct value of one element, which was added to list
     */
    @Test
    public void additionOneElementTest() throws ValueIsInListException, IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 47);
        assertEquals((Integer) 47, list.getFromIndex(1));
    }

    /**
     * Tries to added element to incorrect place
     */
    @Test (expected = IncorrectIndexException.class)
    public void incorrectAdditionTest() throws ValueIsInListException, IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(2, 47);
        assertEquals(0, list.getSize());
    }

    /**
     * Adds to elements with equal values and remove first of it
     */
    @Test
    public void additionTwoEqualElementsAndRemovalOneOfItTest()
            throws IncorrectIndexException, ValueIsInListException, NoValueInListException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 47);
        list.add(2, 47);
        list.add(2, 35);
        list.removeFromValue((Integer) 47);
        assertEquals((Integer) 47, list.getFromIndex(2));
    }

    /**
     * Removes all elements and element from empty list
     */
    @Test (expected = IncorrectIndexException.class)
    public void additionAndRemovalAllElementsTest()
            throws ValueIsInListException, IncorrectIndexException, NoValueInListException {
        List<Character> list = new SingleLinkedList<>();
        list.add(1, 's');
        list.add(1, 'g');
        list.removeFromIndex(2);
        list.removeFromValue('g');
        list.removeFromIndex(12);
    }

    /**
     * Check correct work of method is in list
     */
    @Test
    public void isInListTest() throws ValueIsInListException, IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 19);
        assertTrue(list.isInList(15));
    }

    /**
     * tries to remove element, which didn't situated
      *@throws ValueIsInListException
     * @throws IncorrectIndexException when index of value to remove is incorrect
     * @throws NoValueInListException when there is no value to remove
     */
    @Test (expected = NoValueInListException.class)
    public void removalNotSituatedElement() throws ValueIsInListException, IncorrectIndexException, NoValueInListException {
        List<Character> list = new SingleLinkedList<>();
        list.add(1, '7');
        list.removeFromValue('y');
        assertEquals(1, list.getSize());
    }

    /**
     * Adds, removes and adds value again
     * Check correct size of list
     */
    @Test
    public void additionAndRemovalCheckSizeTest(){
        List<Character> list = new SingleLinkedList<>();
        try{
            list.add(1, 'a');
            list.add(2, 'b');
            list.add(3, 'c');
            list.removeFromValue('c');
            list.add(1, 'c');
            assertEquals(3, list.getSize());
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds, removes and adds value again
     * Check correct place of added value
     */
    @Test
    public void additionAndRemovalCheckFirstTest(){
        List<Character> list = new SingleLinkedList<>();
        try{
            list.add(1, 'a');
            list.add(2, 'b');
            list.add(3, 'c');
            list.removeFromValue('c');
            list.add(1, 'c');
            assertEquals((Character)'c', list.getFromIndex(1));
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Adds, removes and adds value again
     * Check correct value on place, where value was after removal
     */
    @Test
    public void additionAndRemovalCheckLastTest(){
        List<Character> list = new SingleLinkedList<>();
        try{
            list.add(1, 'a');
            list.add(2, 'b');
            list.add(3, 'c');
            list.removeFromValue('c');
            list.add(1, 'c');
            assertEquals((Character)'b', list.getFromIndex(3));
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
            e.message();
        }
    }
}