/**
 * Contains method, which sorts array with bubble sort
 */
public class BubbleSorter extends AbstractSorter{
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
