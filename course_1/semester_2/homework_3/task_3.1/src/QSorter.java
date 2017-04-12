public class QSorter extends AbstractSorter{
    @Override
    public void sort(int[] array) {
        qSort(array, 0, array.length - 1);
    }

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

    protected int searchPivot(int[] array, int begin, int end) {
        int toReturn = 0;
        for (int i = begin; i <= end; i++){
            toReturn = toReturn + array[i];
        }
        return toReturn / ((end - begin) + 1);
    }

    /*protected void swap(int[] array, int indexFirst, int indexSecond){
        int temp = array[indexFirst];
        array[indexFirst] = array[indexSecond];
        array[indexSecond] = temp;
    }*/
}
