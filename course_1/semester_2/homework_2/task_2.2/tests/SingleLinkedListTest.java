import org.junit.Test;

import static org.junit.Assert.*;

public class SingleLinkedListTest {
    /**
     * Checks size of created list
     */
    @Test
    public void creationListTest(){
        SingleLinkedList list = new SingleLinkedList();
        assertEquals(0, list.getSize());
    }

    /**
     * Checks list size after addition one element
     * @throws PlaceException
     */
    @Test
    public void additionOneElementCheckSizeTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        assertEquals(1, list.getSize());
    }

    /**
     * Checks value of added element
     * @throws PlaceException
     */
    @Test
    public void additionOneElementCheckElementValueTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        assertEquals(4, list.getFromPlace(1));
    }

    /**
     * Checks size of list after addition two elements
     * @throws PlaceException
     */
    @Test
    public void additionSomeElementsCheckSizeTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        assertEquals(2, list.getSize());
    }

    /**
     * Checks the value of the first element after two additions on the first place
     * @throws PlaceException
     */
    @Test
    public void additionSomeElementsCheckFirstValueTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        assertEquals('a', list.getFromPlace(1));
    }

    /**
     * Checks the value of the second element after two additions on the first place
     * @throws PlaceException
     */
    @Test
    public void additionSomeElementsCheckSecondElementTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        assertEquals(4, list.getFromPlace(2));
    }

    /**
     * Checks value of the third element after three additions
     * @throws NoValueException
     * @throws PlaceException
     */
    @Test
    public void searchPlaceTest() throws NoValueException, PlaceException{
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        list.add(2, 36);
        assertEquals(3, list.searchPlace(4));
    }

    /**
     * Checks the size of list after addition and removal from place
     * @throws PlaceException
     */
    @Test
    public void removeFromPlaceCheckSizeTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        list.add(2, 36);
        list.removeFromPlace(1);
        assertEquals(2, list.getSize());
    }

    /**
     * Checks the size of list after addition and removal from value
     * @throws PlaceException
     */
    @Test
    public void removeFromValueCheckSizeTest() throws PlaceException, NoValueException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        list.add(2, 36);
        list.removeFromValue(36);
        assertEquals(2, list.getSize());
    }

    /**
     * Tries to get element from empty list
     * @throws PlaceException
     */
    @Test
    public void searchInEmptyListTest() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        try {
            assertNotEquals(3, list.getFromPlace(1));
        } catch (PlaceException e) {
           assertEquals(0, list.getSize());
        }
    }

    /**
     * Tries to get element from place, which is not situated
     * @throws PlaceException
     */
    @Test
    public void searchFromNotCorrectPlace() throws PlaceException {
        SingleLinkedList list = new SingleLinkedList();
        list.add(1, 4);
        list.add(1, 'a');
        list.add(2, 36);
        try {
            assertNotEquals('s', list.getFromPlace(4));
        } catch (PlaceException e) {
            assertNotEquals(4, list.getSize());
        }
    }
}