abstract public class AbstractSorter implements Sorter {
    @Override
    public int[] getArray(){
        //TO DO
        return null;
    }

    @Override
    abstract public void sort(int[] array);

    /**
     * Changes places of two elements
     * @param array
     * @param indexFirst place of the fisrt element, where will be the second element
     * @param indexSecond place of the second element, where will be the first element
     */
    protected void swap(int[] array, int indexFirst, int indexSecond){
        int temp = array[indexFirst];
        array[indexFirst] = array[indexSecond];
        array[indexSecond] = temp;
    }
}
