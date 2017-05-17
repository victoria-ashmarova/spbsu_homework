import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayReader {

    public Scanner getFile() throws FileNotFoundException {
        System.out.println("Enter the name of file with array:");
        Scanner scanConsole = new Scanner(System.in);
        String name = scanConsole.next();
        Scanner scan = new Scanner(new File(name));
        return scan;
    }

    public Object[][] makeArray(Scanner scan) throws NoTableException{
        try{
            int size = getSize(scan);
            Object array[][] = new Object[size][];
            for (int i = 0; i < size; i++){
                array[i] = new Object[size];
            }
            return array;
        } catch (IncorrectSizeException e){
            throw new NoTableException();
        }
    }

    public void fullArray(Object array[][], Scanner scan) {
        int size = array.length;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                array[i][j] = scan.next();
            }
        }
    }

    private int getSize(Scanner scan) throws IncorrectSizeException{
        try {
            int size = scan.nextInt();
            if (size % 2 == 0 || size < 1) {
                throw new IncorrectSizeException();
            }
            return size;
        } catch (InputMismatchException e){
            throw new IncorrectSizeException();
        }
    }
}
