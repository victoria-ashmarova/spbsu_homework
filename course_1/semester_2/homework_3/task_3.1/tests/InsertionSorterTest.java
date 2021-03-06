import org.junit.Test;

import static org.junit.Assert.*;

public class InsertionSorterTest {
    /**
     * tests search of correct place to element
     */
    @Test
    public void searchPlaceTest(){
        InsertionSorter iSorter = new InsertionSorter();
        int array[] = {6, 1, 4};
        int place = iSorter.searchPlace(array, 1);
        assertEquals(0, place);
    }

    /**
     * tests insertion sort
     */
    @Test
    public void InsertionSortTest() throws NoArrayException {
        int toSort[] = {7, 6, 4, 5, 3, 2, 1};
        int expected[] = {1, 2, 3, 4, 5, 6, 7};
        InsertionSorter iSorter = new InsertionSorter();
        iSorter.sort(toSort);
        assertArrayEquals(expected, toSort);
    }
}