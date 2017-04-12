abstract public class AbstractSorter implements Sorter {
    @Override
    public int[] getArray(){
        //TO DO
        return null;
    }

    @Override
    abstract public void sort(int[] array);

    protected void swap(int[] array, int indexFirst, int indexSecond){
        int temp = array[indexFirst];
        array[indexFirst] = array[indexSecond];
        array[indexSecond] = temp;
    }
}
