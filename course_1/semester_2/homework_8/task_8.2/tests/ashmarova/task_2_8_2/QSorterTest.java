package ashmarova.task_2_8_2;

import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * Class contains tests for single and multi threads realizations of quick sort
 */
public class QSorterTest {
    private final int ARRAY_SIZE = 1024 * 1024;
    private final int MAX_VALUE = 256;
    private final Random generator = new Random();
    private final long MAX_TIME_DIFFERENCE = 100;
    private final int LIMIT_OF_THREADS = 8;

    /**
     * generates array with integer elements
     * @return array with integer elements
     */
    private Integer[] createRandomArray() {
        Integer array[] = new Integer[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = generator.nextInt(MAX_VALUE);
        }
        return array;
    }

    /**
     * checks correctness of result of single thread quick sort
     */
    @Test
    public void correctnessSingleThreadSortTest() {
        QSorter<Integer> qSorter = new SingleThreadQuickSorter<>(Integer::compareTo);
        Integer array[] = createRandomArray();
        qSorter.sort(array);
        for (int i = 0; i < array.length - 2; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    /**
     * checks correctness of result of multi thread quick sort
     */
    @Test
    public void correctnessMultiThreadSortTest() {
        QSorter<Integer> qSorter = new MultiThreadQuickSorter<>(Integer::compareTo, LIMIT_OF_THREADS);
        Integer array[] = createRandomArray();
        qSorter.sort(array);
        for (int i = 0; i < array.length - 2; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    /**
     * checks, that multi thread quick sort is faster, then single thread quick sort,
     * or time of single quick sort realization is not so different with time of
     * multi quick sort realization.
     * also prints some data: time to sort realizations, max amount of threads and size of array to sort.
     */
    @Test
    public void timeTest() {
        Integer arrayForSingleThreadSort[] = createRandomArray();
        Integer arrayForMultiThreadSort[] = arrayForSingleThreadSort.clone();

        QSorter<Integer> singleQuickSorter = new SingleThreadQuickSorter<>(Integer::compareTo);
        QSorter<Integer> multiQuickSorter = new SingleThreadQuickSorter<>(Integer::compareTo);

        long start = System.currentTimeMillis();
        singleQuickSorter.sort(arrayForSingleThreadSort);
        long singleThreadSortFinish = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        multiQuickSorter.sort(arrayForMultiThreadSort);
        long multiThreadSortFinish = System.currentTimeMillis() - start;

        System.out.print(" Time of multi thread quick sort is " + multiThreadSortFinish + " m.s" +
                "\n Time of single thread quick sort is " + singleThreadSortFinish + " m.s" +
                "\n Size of array is " + ARRAY_SIZE + "; Max amount of threads is " + LIMIT_OF_THREADS);

        assertTrue(Math.abs(multiThreadSortFinish - singleThreadSortFinish) < MAX_TIME_DIFFERENCE ||
                multiThreadSortFinish < singleThreadSortFinish);
    }
}