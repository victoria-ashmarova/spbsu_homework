public class InsertionSorter extends AbstractSorter{
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++){
            int newPlace = searchPlace(array, i);
            move(array, newPlace, i);
        }
    }

    protected void move(int array[], int begin, int end){
        for (int i = end; i > begin; i--){
            swap(array, i, i - 1);
        }
    }

    protected int searchPlace(int array[], int currentPlace){
        int newPlace = 0;
        while (array[newPlace] < array[currentPlace]){
            newPlace++;
        }
        return newPlace;
    }
}
