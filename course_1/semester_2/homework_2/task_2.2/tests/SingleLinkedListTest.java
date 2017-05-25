/**
 * Class contains tests of methods single linked list.
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleLinkedListTest {
    /**
     * Checks size of created list.
     */
    @Test
    public void creationListTest(){
        List<Integer> list = new SingleLinkedList<>();
        assertEquals(0, list.getSize());
    }

    /**
     * Checks list size after addition one element.
     * @throws IncorrectIndexException in add method
     */
    @Test
    public void additionOneElementCheckSizeTest() throws IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        assertEquals(1, list.getSize());
    }

    /**
     * Checks value of added element.
     * @throws IncorrectIndexException in add method
     * @throws AvailableValueException in get woth index method
     */
    @Test
    public void additionOneElementCheckElementValueTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        assertEquals((Integer) 4, list.getWithIndex(1));
    }

    /**
     * Checks size of list after addition two elements.
     * @throws IncorrectIndexException in addition method
     */
    @Test
    public void additionSomeElementsCheckSizeTest() throws IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, 616);
        assertEquals(2, list.getSize());
    }

    /**
     * Checks the value of the first element after two additions on the first place.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void additionSomeElementsCheckFirstValueTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        assertEquals((Integer) (-5), list.getWithIndex(1));
    }

    /**
     * Checks the value of the second element after two additions on the first place.
     * @throws IncorrectIndexException in add method
     * @throws AvailableValueException in get with method
     */
    @Test
    public void additionSomeElementsCheckSecondElementTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        assertEquals((Integer) 4, list.getWithIndex(2));
    }

    /**
     * Checks value of the third element after three additions.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in search index method
     */
    @Test
    public void searchPlaceTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        list.add(2, 36);
        assertEquals(3, list.searchIndex(4));
    }

    /**
     * Checks the size of list after addition and removal from place.
     * @throws IncorrectIndexException in add and remove method
     */
    @Test
    public void removeFromPlaceCheckSizeTest() throws IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        list.add(2, 36);
        list.removeWithIndex(1);
        assertEquals(2, list.getSize());
    }

    /**
     * Checks the size of list after addition and removal from value.
     * @throws IncorrectIndexException in add method
     * @throws AvailableValueException in remove method
     */
    @Test
    public void removeFromValueCheckSizeTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        list.add(2, 36);
        list.removeFromValue(36);
        assertEquals(2, list.getSize());
    }

    /**
     * Tries to get element from empty list.
     * @throws IncorrectIndexException in get with index method
     * @throws AvailableValueException in get with index method
     */
    @Test (expected = AvailableValueException.class)
    public void searchInEmptyListTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        assertNotEquals((Integer) 3, list.getWithIndex(1));
        assertEquals(0, list.getSize());
    }

    /**
     * Tries to get element from place, which is not situated.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void searchFromNotCorrectIndex() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new SingleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        list.add(2, 36);
        list.getWithIndex(4);
        assertNotEquals(4, list.getSize());
    }
}