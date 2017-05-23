import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {
    /**
     * Check correct work method is in list
     */
    @Test
    public void isInListTest() throws ValueIsInListException, IncorrectIndexException {
        List<Integer> list = new UniqueList<>();
        list.add(1, 34);
        assertTrue(list.isInList(34));
    }

    /**
     * checks that there is no ability to add two equal element to the same place
     */
    @Test (expected = ValueIsInListException.class)
    public void additionTwoEqualsElementsInBeginTest() throws ValueIsInListException, IncorrectIndexException {
        List<Integer> list = new UniqueList<>();
        list.add(1, 145);
        list.add(1, 145);
        assertEquals(1, list.getSize());
    }

    /**
     * checks, that there is no ability to add equal element to places with different index
     */
    @Test (expected = ValueIsInListException.class)
    public void additionTwoEqualElementsOnDifferentPlaces() throws ValueIsInListException, IncorrectIndexException {
        List<Character> list = new UniqueList<>();
        list.add(1, 'd');
        list.add(2, 's');
        list.add(1, 'a');
        list.add(3, 'd');
        assertEquals(3, list.getSize());
    }

    /**
     * Checks, that there is ability to add value after its removal
     */
    @Test
    public void addRemoveAndAddTest() throws ValueIsInListException, IncorrectIndexException, NoValueInListException {
        List<Character> list = new UniqueList<>();
        list.add(1, 'd');
        list.add(2, 's');
        list.add(1, 'a');
        list.removeFromValue('d');
        list.add(3, 'd');
        assertEquals(3, list.searchIndex('d'));
        assertEquals(3, list.getSize());
    }
}