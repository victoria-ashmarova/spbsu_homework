/**
 * Contains method, which implements quick sort
 */
public class QSorter extends AbstractSorter{
    /**
     * Method, which sorts array with algorithm of quick sort
     * @throws NoArrayException when there is no array to sort
     * @param array is array to sort
     */
    @Override
    public void sort(int[] array) throws NoArrayException {
        if (array == null){
            throw new NoArrayException();
        }
        qSort(array, 0, array.length - 1);
    }

    /**
     * Replaces elements with small value in begin of array, and elements with big value in the end of array
     * and then sorts these twi parts of array
     * @param array to sort
     * @param begin begin index of part to sort
     * @param end end index of part to sort
     */
    private void qSort(int[] array, int begin, int end){
        int pivot = searchPivot(array, begin, end);
        int i = begin;
        int j = end;
        while (i < j){
            while (array[i] < pivot){
                i++;
            }
            while (array[j] > pivot){
                j--;
            }
            if (i <= j){
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (begin < j){
            qSort(array, begin, j);
        }
        if (end > i){
            qSort(array, i, end);
        }
    }

    /**
     * Searches pivot of quicksort
     * @param array to sort
     * @param begin begin index of part of array
     * @param end end index of part of array
     * @return
     */
    protected int searchPivot(int[] array, int begin, int end) {
        int toReturn = 0;
        for (int i = begin; i <= end; i++){
            toReturn = toReturn + array[i];
        }
        return toReturn / ((end - begin) + 1);
    }
}
