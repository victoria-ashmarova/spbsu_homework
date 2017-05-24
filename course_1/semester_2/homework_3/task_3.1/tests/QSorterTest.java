import org.junit.Test;

import static org.junit.Assert.*;

public class QSorterTest {
    /**
     * tests quick sort of array with odd number of elements
     */
    @Test
    public void qSortTest() {
        int toSort[] = {4, 3, 5, 2, 1};
        int expected[] = {1, 2, 3, 4, 5};
        QSorter qSorter = new QSorter();
        try {
            qSorter.sort(toSort);
        } catch (NoArrayException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected, toSort);
    }

    /**
     * tests correct result of swap
     */
    @Test
    public void swapTest(){
        QSorter qSorter = new QSorter();
        int array[] = {1, 2};
        int expected[] = {2, 1};
        qSorter.swap(array, 0, 1);
        assertArrayEquals(expected, array);
    }

    /**
     * tests correct search of pivot
     */
    @Test
    public void searchPivotTest(){
        QSorter qSorter = new QSorter();
        int array[] = {1, 2, 4, 5};
        int pivot = qSorter.searchPivot(array, 0, 3);
        assertEquals(3, pivot);
    }

    /**
     * tests quick sort of array with even number of elements
     */
    @Test
    public void qSortSortedArrayTest() throws NoArrayException {
        int toSort[] = {2, 4, 5, 7};
        int expected[] = {2, 4, 5, 7};
        QSorter qSorter = new QSorter();
        qSorter.sort(toSort);
        assertArrayEquals(expected, toSort);
    }
}