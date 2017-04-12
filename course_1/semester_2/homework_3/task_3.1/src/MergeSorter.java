public class MergeSorter extends AbstractSorter{
    @Override
    public void sort(int[] array) {
        int buffer[] = new int[array.length];
        merge(array, buffer, 0, newCentre(0, array.length  - 1) ,array.length - 1);
        for (int i = 0; i < array.length; i++){
            array[i] = buffer[i];
        }
    }

    private int newCentre(int begin, int end){
        return end -  (end - begin) / 2;
    }

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
