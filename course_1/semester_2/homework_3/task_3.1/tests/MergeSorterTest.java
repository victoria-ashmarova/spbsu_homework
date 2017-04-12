import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSorterTest {
    @Test
    public void sortTwoElementsTest(){
        int array[] = {3, 2};
        int expected[] = {2, 3};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, expected);
    }

    @Test
    public void sortFourElementsTest(){
        int array[] = {1, 2, 0, 3};
        int expected[] = {0, 1, 2, 3};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void sortThreeElementsTest(){
        int array[] = {3, 1, 2};
        int expected[] = {1, 2, 3};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void mergeSortTest(){
        int array[] = {9, 8, 7, 6, 5, 4, 3};
        int expected[] = {3, 4, 5, 6, 7, 8, 9};
        MergeSorter mSort = new MergeSorter();
        mSort.sort(array);
        assertArrayEquals(expected, array);
    }
}