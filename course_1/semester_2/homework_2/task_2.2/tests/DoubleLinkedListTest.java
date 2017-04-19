import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleLinkedListTest {
    /**
     * Checks zero size of list after creation
     */
    @Test
    public void creationListTest(){
        DoubleLinkedList list = new DoubleLinkedList();
        assertEquals(0, list.getSize());
    }

    /**
     * Checks size list after addition one element
     * @throws PlaceException
     */
    @Test
    public void additionOneElementCheckSizeTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 4);
        assertEquals(1, list.getSize());
    }

    /**
     * Checks correct after of element after addition
     * @throws PlaceException
     */
    @Test
    public void additionOneElementCheckValueTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 42);
        assertEquals(42, list.getFromPlace(1));
    }

    /**
     * Checks size of list after addition two elements
     */
    @Test
    public void additionTwoElementsCheckSizeTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 42);
        list.add(1, 169);
        assertEquals(2, list.getSize());
    }

    /**
     * Checks size of list after addition two elements
     */
    @Test
    public void additionTwoElementsCheckFirstTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 42);
        list.add(1, 169);
        assertEquals(169, list.getFromPlace(1));
    }

    /**
     * Checks size of list after addition two elements
     */
    @Test
    public void additionTwoElementsCheckSecondTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 42);
        list.add(1, 169);
        assertEquals(42, list.getFromPlace(2));
    }

    /**
     * Tries to get element from place, which is not situated
     * @throws PlaceException
     */
    @Test
    public void searchFromNotCorrectPlaceTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        list.add(2, 36);
        try {
            assertNotEquals('s', list.getFromPlace(4));
        } catch (PlaceException e) {
            assertNotEquals(4, list.getSize());
        }
    }

    /**
     * Tries to get element from empty list
     * @throws PlaceException
     */
    @Test
    public void searchInEmptyListTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        try {
            assertNotEquals(3, list.getFromPlace(1));
        } catch (PlaceException e) {
            assertEquals(0, list.getSize());
        }
    }

    /**
     * Checks zero size after deleting all elements
     * @throws PlaceException
     */
    @Test
    public void removalAllElementTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 16);
        for (int i = 0; i < 3; i++){
            list.removeFromPlace(1);
        }
        assertEquals(0, list.getSize());
    }

    @Test
    public void checkElementBeforeRemovedTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 16);
        list.removeFromPlace(2);
        assertEquals(16, list.getFromPlace(1));
    }

    @Test
    public void checkElementAfterRemovedTest() throws PlaceException {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1, 14);
        list.add(1, 15);
        list.add(1, 16);
        list.removeFromPlace(2);
        assertEquals(14, list.getFromPlace(2));
    }
}