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
     */
    @Test
    public void additionOneElementCheckSizeTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            assertEquals(1, list.getSize());
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks value of added element.
     */
    @Test
    public void additionOneElementCheckElementValueTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            assertEquals((Integer) 4, list.getWithIndex(1));
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks size of list after addition two elements.
     */
    @Test
    public void additionSomeElementsCheckSizeTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            list.add(1, 616);
            assertEquals(2, list.getSize());
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks the value of the first element after two additions on the first place.
     */
    @Test
    public void additionSomeElementsCheckFirstValueTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            list.add(1, -5);
            assertEquals((Integer) (-5), list.getWithIndex(1));
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks the value of the second element after two additions on the first place.
     */
    @Test
    public void additionSomeElementsCheckSecondElementTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            list.add(1, -5);
            assertEquals((Integer) 4, list.getWithIndex(2));
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks value of the third element after three additions.
     */
    @Test
    public void searchPlaceTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            list.add(1, -5);
            list.add(2, 36);
            assertEquals(3, list.searchIndex(4));
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks the size of list after addition and removal from place.
     */
    @Test
    public void removeFromPlaceCheckSizeTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            list.add(1, -5);
            list.add(2, 36);
            list.removeWithIndex(1);
            assertEquals(2, list.getSize());
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks the size of list after addition and removal from value.
     */
    @Test
    public void removeFromValueCheckSizeTest(){
        try {
            List<Integer> list = new SingleLinkedList<>();
            list.add(1, 4);
            list.add(1, -5);
            list.add(2, 36);
            list.removeFromValue(36);
            assertEquals(2, list.getSize());
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Tries to get element from empty list.
     */
    @Test
    public void searchInEmptyListTest() {
        List<Integer> list = new SingleLinkedList<>();
        try {
            assertNotEquals((Integer) 3, list.getWithIndex(1));
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        } finally {
            assertEquals(0, list.getSize());
        }
    }

    /**
     * Tries to get element from place, which is not situated.
     */
    @Test
    public void searchFromNotCorrectIndex() throws IncorrectIndexException {
        List<Integer> list = new SingleLinkedList<>();
        try {
            list.add(1, 4);
            list.add(1, -5);
            list.add(2, 36);
            list.getWithIndex(4);
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        } finally {
            assertNotEquals(4, list.getSize());
        }
    }
}