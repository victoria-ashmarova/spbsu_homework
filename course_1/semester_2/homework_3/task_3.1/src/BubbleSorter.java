/**
 * Contains method, which sorts array with bubble sort
 */
public class BubbleSorter extends AbstractSorter{
    /**
     * Method, which sorts array with bubble sort algorithm
     * @throws NoArrayException when there is no array to sort
     * @param array is array to sort
     */
    @Override
    public void sort(int[] array) throws NoArrayException{
        if (array == null){
            throw new NoArrayException();
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < (array.length - i - 1); j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
