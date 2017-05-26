import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class, which contains methods, which get array from console
 */
public class Reader {
    /**
     * Method, which get array from console
     * @throws NoArrayException when size of array of type of data to sort are incorrect
     * @return array fot sort
     */
    public static int[] getArray() throws NoArrayException {
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
            e.message();
            throw new NoArrayException();
        } catch (InputMismatchException e){
            System.out.print("Incorrect array elements.\n");
            throw new NoArrayException();
        }
    }

    /**
     * gets array size from scanner
     * @param scan is resourse with array is is got
     * @return array size
     * @throws IncorrectArraySizeException when size of array is negative value or type of it is incorrect
     */
    private static int getArraySize(Scanner scan) throws IncorrectArraySizeException{
        int size = 0;
        try {
            System.out.print("Enter the size of array - positive integer value\n");
            size = scan.nextInt();
            if (size <= 0){
                throw new IncorrectArraySizeException("Size couldn't be negative value");
            }
            return size;
        } catch (InputMismatchException e){
            throw new IncorrectArraySizeException("Incorrect type of value with size.");
        }
    }
}
