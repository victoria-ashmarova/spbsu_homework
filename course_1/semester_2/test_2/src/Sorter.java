import java.util.Comparator;

/**
 * Class contains method, which sorts array with bubble sort algorithm
 * @param <T> is type of data to sort
 */
public class Sorter<T> {
    /**
     * sort array, using bubble sort algorithm
     * @param array is array to sort
     * @param comparator is object, which gives ability of comparing array elements
     */
    public void sort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[i]) < 0) {
                    swap(array, i, j);
                }
            }
        }
    }

    /**
     * swaps two elements in array
     * @param array is array to sort
     * @param firstIndex index of first element to swap
     * @param secondIndex index of second element to swap
     */
    private void swap(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
