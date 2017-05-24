import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleSorterTest {
    /**
     * tests bubble sort
     */
    @Test
    public void bubbleSortTest() throws NoArrayException {
        int toSort[] = {1, 7, 2, 4, 6, 3, 8};
        int expected[] = {1, 2, 3, 4, 6, 7, 8};
        BubbleSorter bSorter = new BubbleSorter();
        bSorter.sort(toSort);
        assertArrayEquals(expected, toSort);
    }
}