import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {
    /**
     * Check correct work method is in list
     */
    @Test
    public void isInListTest(){
        List<Integer> list = new UniqueList<>();
        try {
            list.add(1, 34);
            assertTrue(list.isInList(34));
        } catch (PlaceException e) {
            e.printStackTrace();
        } catch (ValueIsInListException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks that there is no ability to add two equal element to the same place
     */
    @Test
    public void additionTwoEqualsElementsInBeginTest(){
        List<Integer> list = new UniqueList<>();
        try {
            list.add(1, 145);
            list.add(1, 145);
        } catch (PlaceException e) {
            e.printStackTrace();
        } catch (ValueIsInListException e) {
            e.printStackTrace();
        } finally {
            assertEquals(1, list.getSize());
        }
    }

    /**
     * checks, that there is no ability to add equal element to places with different index
     */
    @Test
    public void additionTwoEqualElementsOnDifferentPlaces(){
        List<Character> list = new UniqueList<>();
        try{
            list.add(1, 'd');
            list.add(2, 's');
            list.add(1, 'a');
            list.add(3, 'd');
        } catch (ValueIsInListException e) {
            e.printStackTrace();
        } catch (PlaceException e) {
            e.printStackTrace();
        } finally {
            assertEquals(3, list.getSize());
        }
    }

    /**
     * Checks, that there is ability to add value after its removal
     */
    @Test
    public void addRemoveAndAddTest(){
        List<Character> list = new UniqueList<>();
        try{
            list.add(1, 'd');
            list.add(2, 's');
            list.add(1, 'a');
            list.removeFromValue('d');
            list.add(3, 'd');
            assertEquals(3, list.searchPlace('d'));
        } catch (ValueIsInListException e) {
            e.printStackTrace();
        } catch (PlaceException e) {
            e.printStackTrace();
        } catch (NoValueInListException e) {
            e.printStackTrace();
        } finally {
            assertEquals(3, list.getSize());
        }
    }
}