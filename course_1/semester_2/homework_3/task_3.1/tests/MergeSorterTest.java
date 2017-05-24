import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSorterTest {
    /**
     * tests sort of array with two elements
     */
    @Test
    public void sortTwoElementsTest() throws NoArrayException {
        int array[] = {3, 2};
        int expected[] = {2, 3};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, expected);
    }

    /**
     * tests sort of array with number of elements, which is equal to some power of 2 (4 = 2^2)
     */
    @Test
    public void sortFourElementsTest() throws NoArrayException {
        int array[] = {1, 2, 0, 3};
        int expected[] = {0, 1, 2, 3};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, array);
    }

    /**
     * tests sort of 3 elements
     */
    @Test
    public void sortThreeElementsTest() throws NoArrayException {
        int array[] = {3, 1, 2};
        int expected[] = {1, 2, 3};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, array);
    }

    /**
     * tests sort of big random array
     */
    @Test
    public void mergeSortTest() throws NoArrayException {
        int array[] = {9, 8, 7, 6, 5, 4, 3};
        int expected[] = {3, 4, 5, 6, 7, 8, 9};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, array);
    }
}