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
    public void additionOneElementTest(){
        List<Integer> list = new SingleLinkedList<>();
        try {
            list.add(1, 47);
            assertEquals((Integer) 47, list.getFromPlace(1));
        } catch (PlaceException e) {
            e.printStackTrace();
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Tries to added element to incorrect place
     */
    @Test
    public void incorrectAdditionTest(){
        List<Integer> list = new SingleLinkedList<>();
        try {
            list.add(2, 47);
        } catch (PlaceException e) {
            e.printStackTrace();
            e.message();
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } finally {
            assertEquals(0, list.getSize());
        }
    }

    /**
     * Adds to elements with equal values and remove first of it
     */
    @Test
    public void additionTwoEqualElementsAndRemovalOneOfItTest(){
        List<Integer> list = new SingleLinkedList<>();
        try {
            list.add(1, 47);
            list.add(2, 47);
            list.add(2, 35);
            list.removeFromValue((Integer) 47);
            assertEquals((Integer) 47, list.getFromPlace(2));
        } catch (PlaceException e) {
            e.printStackTrace();
            e.message();
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Removes all elements and element from empty list
     */
    @Test
    public void additionAndRemovalAllElementsTest(){
        List<Character> list = new SingleLinkedList<>();
        try{
            list.add(1, 's');
            list.add(1, 'g');
            list.removeFromPlace(2);
            list.removeFromValue('g');
            list.removeFromPlace(12);
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (PlaceException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Check correct work of method is in list
     */
    @Test
    public void isInListTest(){
        List<Integer> list = new SingleLinkedList<>();
        try {
            list.add(1, 14);
            list.add(1, 15);
            list.add(1, 19);
            assertTrue(list.isInList(15));
        } catch (PlaceException e) {
            e.printStackTrace();
            e.message();
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        }

    }

    /**
     * tries to remove element, which didn't situated
     */
    @Test
    public void removalNotSituatedElement(){
        List<Character> list = new SingleLinkedList<>();
        try{
            list.add(1, '7');
            list.removeFromValue('y');
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (PlaceException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
            e.message();
        } finally {
            assertEquals(1, list.getSize());
        }
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
        } catch (PlaceException e) {
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
            assertEquals((Character)'c', list.getFromPlace(1));
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (PlaceException e) {
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
            assertEquals((Character)'b', list.getFromPlace(3));
        } catch (ValueIsInListException e) {
            e.printStackTrace();
            e.message();
        } catch (PlaceException e) {
            e.printStackTrace();
            e.message();
        } catch (NoValueInListException e) {
            e.printStackTrace();
            e.message();
        }
    }
}