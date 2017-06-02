package ashmarova.task_2_8_2;

import java.util.Comparator;

/**
 * Contains realization of quick sort with one thread.
 * @param <T> is type of data to sort
 */
public class SingleThreadQuickSorter<T> extends QSorter<T>{
    /**
     * extends constructor of super class
     * @param comparator to compare elements of array
     */
    SingleThreadQuickSorter(Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * sorts array, using quick sort algorithm for one thread.
     * @param array is data to sort
     */
    @Override
    public void sort(T[] array) {
        singleThreadQuickSort(array, 0, array.length - 1);
    }

    private void singleThreadQuickSort(T[] array, int left, int right) {
        int separator = partOfSort(array, left, right);

        if (left == right - 1) {
            if (comparator.compare(array[left], array[right]) > 0) {
                T temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
            return;
        }

        if (left < separator - 1) {
            singleThreadQuickSort(array, left, separator - 1);
        }

        if (right > separator) {
            singleThreadQuickSort(array, separator, right);
        }
    }
}
