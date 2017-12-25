import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class AbstractSorter implements Sorter {
    /**
     * Method, which sorts array with some algorithm
     * @throws NoArrayException when there is no array to sort
     * @param array is array to sort
     */
    @Override
    abstract public void sort(int[] array) throws NoArrayException;

    /**
     * Changes places of two elements
     * @param array is array to sort
     * @param indexFirst place of the fisrt element, where will be the second element
     * @param indexSecond place of the second element, where will be the first element
     */
    protected void swap(int[] array, int indexFirst, int indexSecond){
        int temp = array[indexFirst];
        array[indexFirst] = array[indexSecond];
        array[indexSecond] = temp;
    }

    /**
     * Method, which prints sorted array to console
     * @throws NoArrayException when there is no array to print
     * @param array to print
     */
    @Override
    public void print(int[] array) throws NoArrayException {
        if (array == null){
            throw new NoArrayException();
        }
        System.out.print("Sorted array:\n");
        for (int i = 0; i < array.length; i++) {
            System.out.print((i + 1) + ") " + array[i] + "; ");
        }
    }
}
