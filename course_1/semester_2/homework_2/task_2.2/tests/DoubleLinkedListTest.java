/**
 * Class contains tests of methods double linked list.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleLinkedListTest {
    /**
     * Checks zero size of list after creation.
     */
    @Test
    public void creationListTest(){
        List<Integer> list = new DoubleLinkedList<>();
        assertEquals(0, list.getSize());
    }

    /**
     * Checks size list after addition one element
     * @throws IncorrectIndexException in addition method
     */
    @Test
    public void additionOneElementCheckSizeTest() throws IncorrectIndexException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 4);
        assertEquals(1, list.getSize());
    }

    /**
     * Checks correct after of element after addition.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void additionOneElementCheckValueTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 42);
        assertEquals((Integer) 42, list.getWithIndex(1));
    }

    /**
     * Checks size of list after addition two elements.
     * @throws IncorrectIndexException in addition method
     */
    @Test
    public void additionTwoElementsCheckSizeTest() throws IncorrectIndexException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 42);
        list.add(1, 169);
        assertEquals(2, list.getSize());
    }

    /**
     * Checks size of list after addition two elements.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void additionTwoElementsCheckFirstTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 42);
        list.add(1, 169);
        assertEquals((Integer)169, list.getWithIndex(1));
    }

    /**
     * Checks size of list after addition two elements.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void additionTwoElementsCheckSecondTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 42);
        list.add(1, 169);
        assertEquals((Integer)42, list.getWithIndex(2));
    }

    /**
     * Tries to get element from place, which is not situated.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void searchFromNotCorrectPlaceTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 4);
        list.add(1, -5);
        list.add(2, 36);
        assertNotEquals((Integer)42, list.getWithIndex(4));
        assertNotEquals(4, list.getSize());
    }

    /**
     * Tries to get element from empty list.
     * @throws IncorrectIndexException in addition method
     * @throws AvailableValueException in get with index method
     */
    @Test (expected = AvailableValueException.class)
    public void searchInEmptyListTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.getWithIndex(1);
        assertEquals(0, list.getSize());
    }

    /**
     * Checks zero size after deleting all elements.
     * @throws IncorrectIndexException in add and femove methods
     */
    @Test
    public void removalAllElementTest() throws IncorrectIndexException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 16);
        for (int i = 0; i < 3; i++) {
            list.removeWithIndex(1);
        }
        assertEquals(0, list.getSize());
    }

    /**
     * Checks correct value of current first element after deleting last first element.
     * @throws IncorrectIndexException in add and remove methods
     * @throws AvailableValueException in get with index method
     */
    @Test
    public void checkingFirstElementAfterRemovalTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 16);
        list.removeWithIndex(2);
        assertEquals((Integer) 16, list.getWithIndex(1));
    }

    /**
     * Checks correct value of current not first element after deleting last element with this index.
     * @throws IncorrectIndexException in add method
     * @throws AvailableValueException in remove method
     */
    @Test
    public void checkingNotFirstElementAfterRemovalTest() throws IncorrectIndexException, AvailableValueException {
        List<Integer> list = new DoubleLinkedList<>();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 16);
        list.removeWithIndex(2);
        assertEquals((Integer)14, list.getWithIndex(2));
    }
}