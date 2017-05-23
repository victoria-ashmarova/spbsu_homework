import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class, with contain methods to get table
 */
public class ArrayReader {
    /**
     * gets file(information source) with table
     * @return information source with table
     * @throws FileNotFoundException
     */
    public Scanner getFile() throws FileNotFoundException {
        System.out.println("Enter the name of file with array:");
        Scanner scanConsole = new Scanner(System.in);
        String name = scanConsole.next();
        Scanner scan = new Scanner(new File(name));
        return scan;
    }

    /**
     * @param scan is information source to get table's size
     * @return empty table
     * @throws NoTableException when size of table is incorrect
     */
    public String[][] makeTable(Scanner scan) throws NoTableException{
        try{
            int size = getSize(scan);
            String array[][] = new String[size][];
            for (int i = 0; i < size; i++){
                array[i] =  new String[size];
            }
            return array;
        } catch (IncorrectSizeException e){
            throw new NoTableException();
        }
    }

    /**
     * records data to table from source of information
     * @param table is place to keeping data
     * @param scan is source of information to get values for table
     */
    public void fullArray(String table[][], Scanner scan) {
        int size = table.length;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                table[i][j] = scan.next();
            }
        }
    }

    /**
     * gets first value from information sourse - size of table
     * @param scan is source of information to get size
     * @return size of future table
     * @throws IncorrectSizeException throws, when size is negative or even
     */
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
