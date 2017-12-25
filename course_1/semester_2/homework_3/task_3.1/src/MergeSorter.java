/**
 * Contains methods, which implement merge sort
 */
public class MergeSorter extends AbstractSorter{
    /**
     * Method, which sorts array with algorithm of merge sort
     * @throws NoArrayException when there is no array to sort
     * @param array is array to sort
     */
    @Override
    public void sort(int[] array) throws NoArrayException {
        if (array == null){
            throw new NoArrayException();
        }
        int buffer[] = new int[array.length];
        merge(array, buffer, 0, newCentre(0, array.length  - 1) ,array.length - 1);
        for (int i = 0; i < array.length; i++){
            array[i] = buffer[i];
        }
    }

    /**
     * Searches place to separate part array on two smaller parts
     * @param begin index of begin part of array
     * @param end index of end part of array
     * @return
     */
    private int newCentre(int begin, int end){
        return end -  (end - begin) / 2;
    }

    /**
     * Ceparate array of two parts with sorted elements and merge it
     * @param array array to sort
     * @param buffer place to get merged parts of array
     * @param beginIndex index of begin the first part of array
     * @param centreIndex index of begin the second part of array
     * @param endIndex index of end the second part of array
     */
    protected void merge(int array[], int buffer[], int beginIndex, int centreIndex, int endIndex){
        if (beginIndex == endIndex){
            buffer[beginIndex] = array[beginIndex];
            return;
        }
        if (endIndex - beginIndex > 1){
            merge(array, buffer, beginIndex, newCentre(beginIndex, centreIndex - 1), centreIndex - 1);
            merge(array, buffer, centreIndex, newCentre(centreIndex, endIndex), endIndex);
            for (int i = beginIndex; i <= endIndex; i++){
                array[i] = buffer[i];
            }
        }

        int i = beginIndex;
        int j = centreIndex;
        int index = beginIndex;
        while (i < centreIndex && j <= endIndex){
            int minIndex = indexOfMin(array, i, j);
            buffer[index] = array[minIndex];
            if (minIndex == i){
                i++;
            }
            if (minIndex == j){
                j++;
            }
            index++;
        }
        if (index <= endIndex){
            while (i < centreIndex){
                buffer[index] = array[i];
                i++;
                index++;
            }
            while (j <= endIndex){
                buffer[index] = array[j];
                j++;
                index++;
            }
        }
    }

    protected int indexOfMin(int array[], int i, int j){
        return (array[i] < array[j] ? i : j);
    }
}
