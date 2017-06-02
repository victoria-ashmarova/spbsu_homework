package ashmarova.task_2_8_2;

import java.util.Comparator;

/**
 * Class contains method, which distributes data in part of array, using quick sort.
 * @param <T> is type of data, which is able to be compared
 */
abstract public class QSorter<T> {
    protected final Comparator<T> comparator;

    protected QSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * contains sort method, which is able to be realized with different ways
     * @param array is data to sort
     */
    abstract public void sort(T[] array);

    /**
     * distributes data in part of array, using quick sort.
     */
    protected int partOfSort(T[] array, int begin, int end) {
        T pivot = array[(begin + end) - (begin + end) / 2];
        int left = begin;
        int right = end;

        while (left <= right){
            while (comparator.compare(array[left], pivot) < 0 && left <= right){
                left++;
            }
            while (comparator.compare(array[right], pivot) > 0 && left <= right){
                right--;
            }
            if (left <= right){
                T temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
