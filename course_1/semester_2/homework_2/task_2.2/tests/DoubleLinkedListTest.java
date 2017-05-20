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
     * Checks size list after addition one element.
     */
    @Test
    public void additionOneElementCheckSizeTest() {
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 4);
            assertEquals(1, list.getSize());
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks correct after of element after addition.
     */
    @Test
    public void additionOneElementCheckValueTest(){
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 42);
            assertEquals((Integer) 42, list.getWithIndex(1));
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks size of list after addition two elements.
     */
    @Test
    public void additionTwoElementsCheckSizeTest(){
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 42);
            list.add(1, 169);
            assertEquals(2, list.getSize());
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks size of list after addition two elements.
     */
    @Test
    public void additionTwoElementsCheckFirstTest(){
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 42);
            list.add(1, 169);
            assertEquals((Integer)169, list.getWithIndex(1));
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks size of list after addition two elements.
     */
    @Test
    public void additionTwoElementsCheckSecondTest(){
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 42);
            list.add(1, 169);
            assertEquals((Integer)42, list.getWithIndex(2));
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Tries to get element from place, which is not situated.
     */
    @Test
    public void searchFromNotCorrectPlaceTest(){
        List<Integer> list = new DoubleLinkedList<>();
        try {
            list.add(1, 4);
            list.add(1, -5);
            list.add(2, 36);
            assertNotEquals((Integer)42, list.getWithIndex(4));
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        } finally {
            assertNotEquals(4, list.getSize());
        }
    }

    /**
     * Tries to get element from empty list.
     */
    @Test
    public void searchInEmptyListTest(){
        List<Integer> list = new DoubleLinkedList<>();
        try {
            list.getWithIndex(1);
        } catch (IncorrectIndexException e) {
            assertEquals(0, list.getSize());
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks zero size after deleting all elements.
     */
    @Test
    public void removalAllElementTest(){
        List<Integer> list = new DoubleLinkedList<>();
        try {
            list.add(1, 14);
            list.add(1, 15);
            list.add(1, 16);
            for (int i = 0; i < 3; i++) {
                list.removeWithIndex(1);
            }
            assertEquals(0, list.getSize());
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks correct value of current first element after deleting last first element.
     */
    @Test
    public void checkingFirstElementAfterRemovalTest(){
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 14);
            list.add(1, 15);
            list.add(1, 16);
            list.removeWithIndex(2);
            assertEquals((Integer) 16, list.getWithIndex(1));
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks correct value of current not first element after deleting last element with this index.
     */
    @Test
    public void checkingNotFirstElementAfterRemovalTest(){
        try {
            List<Integer> list = new DoubleLinkedList<>();
            list.add(1, 14);
            list.add(1, 15);
            list.add(1, 16);
            list.removeWithIndex(2);
            assertEquals((Integer)14, list.getWithIndex(2));
        } catch (IncorrectIndexException e){
            e.printStackTrace();
            e.message();
        } catch (AvailableValueException e) {
            e.printStackTrace();
            e.message();
        }
    }
}