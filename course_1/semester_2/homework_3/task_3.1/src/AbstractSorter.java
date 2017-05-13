import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class AbstractSorter implements Sorter {
    /**
     * gets array size from scanner
     * @param scan is resourse with array is is got
     * @return array size
     * @throws NegativeArraySizeException
     */
    private int getArraySize(Scanner scan) throws NegativeArraySizeException{
        int size = 0;
        try {
            System.out.print("Enter the size of array - positive integer value\n");
            size = scan.nextInt();
            if (size <= 0){
                throw new IncorrectArraySizeException();
            }
            return size;
        } catch (InputMismatchException e){
            throw new IncorrectArraySizeException();
        }
    }

    @Override
    public int[] getArray() throws NoArrayException{
        try {
            Scanner scan = new Scanner(System.in);
            int size = getArraySize(scan);
            int array[] = new int[size];
            System.out.print("Enter elements of array - integer values.\n");
            for (int i = 0; i < size; i++) {
                array[i] = scan.nextInt();
            }
            return array;
        } catch (IncorrectArraySizeException e){
            System.out.print("Incorrect value of array size.\n");
            throw new NoArrayException();
        } catch (InputMismatchException e){
            System.out.print("Incorrect array elements.\n");
            throw new NoArrayException();
        }
    }

    @Override
    abstract public void sort(int[] array) throws NoArrayException;

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
