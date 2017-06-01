import ashmarova.task_2_4_1.*;
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
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in get from index method
     */
    @Test
    public void additionOneElementTest() throws ValueIsInListException, IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 47);
        assertEquals((Integer) 47, list.getFromIndex(1));
    }

    /**
     * Tries to add element to incorrect place
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in addition method
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
        list.removeFromValue(47);
        assertEquals((Integer) 47, list.getFromIndex(2));
    }

    /**
     * Removes all elements and element from empty list
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in addition amd removal method
     * @throws NoValueInListException in removal method
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
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in addition and is in list method
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
      *@throws ValueIsInListException in addition method
     * @throws IncorrectIndexException when index of value to remove is incorrect
     * @throws NoValueInListException when there is no value to remove
     */
    @Test (expected = NoValueInListException.class)
    public void removalNotSituatedElement() throws IncorrectIndexException, NoValueInListException, ValueIsInListException {
        List<Character> list = new SingleLinkedList<>();
        list.add(1, '7');
        list.removeFromValue('y');
        assertEquals(1, list.getSize());
    }

    /**
     * Adds, removes and adds value again
     * Check correct size of list
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in addition and removal method
     * @throws NoValueInListException in removal method
     */
    @Test
    public void additionAndRemovalCheckSizeTest() throws ValueIsInListException, IncorrectIndexException, NoValueInListException {
        List<Character> list = new SingleLinkedList<>();
        list.add(1, 'a');
        list.add(2, 'b');
        list.add(3, 'c');
        list.removeFromValue('c');
        list.add(1, 'c');
        assertEquals(3, list.getSize());
    }

    /**
     * Adds, removes and adds value again
     * Check correct place of added value
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in addition and removal method
     * @throws NoValueInListException in removal method
     */
    @Test
    public void additionAndRemovalCheckFirstTest() throws ValueIsInListException, IncorrectIndexException, NoValueInListException {
        List<Character> list = new SingleLinkedList<>();
        list.add(1, 'a');
        list.add(2, 'b');
        list.add(3, 'c');
        list.removeFromValue('c');
        list.add(1, 'c');
        assertEquals((Character)'c', list.getFromIndex(1));
    }

    /**
     * Adds, removes and adds value again
     * Check correct value on place, where value was after removal
     * @throws ValueIsInListException in addition method
     * @throws IncorrectIndexException in addition and removal method
     * @throws NoValueInListException in removal method
     */
    @Test
    public void additionAndRemovalCheckLastTest() throws ValueIsInListException, IncorrectIndexException, NoValueInListException {
        List<Character> list = new SingleLinkedList<>();
        list.add(1, 'a');
        list.add(2, 'b');
        list.add(3, 'c');
        list.removeFromValue('c');
        list.add(1, 'c');
        assertEquals((Character)'b', list.getFromIndex(3));
    }
}