import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AbstractPrinterTest {
    private final Object TABLE[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private final String EXPECTED_CLOCK_WISE = "5 8 9 6 3 2 1 4 7 ";
    private final String EXPECTED_COUNTER_CLOCK_WISE = "5 4 7 8 9 6 3 2 1 ";
    private final String FILE_NAME = "file.txt";

    /**
     * makes string with printing table clock-wise to console
     * @return string, which was made with table
     */
    private String getConsoleResultClockWise() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);

        PrintStream backup = System.out;
        System.setOut(printStream);

        PrinterToConsole printer = new PrinterToConsole();
        printer.printClockWise(TABLE);

        System.setOut(backup);
        printStream.close();

        String result = byteStream.toString();
        return result;
    }

    /**
     * makes string with printing table counter clock-wise to console
     * @return string, which was made with table
     */
    private String getConsoleResultCounterClockWise() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);

        PrintStream backup = System.out;
        System.setOut(printStream);

        PrinterToConsole printer = new PrinterToConsole();
        printer.printCounterClockWise(TABLE);

        System.setOut(backup);
        printStream.close();

        String result = byteStream.toString();
        return result;
    }

    /**
     * makes string with printing table clock-wise to file
     * @return string, which was made with table
     */
    private String getFileResultClockWise() {
        try {
            PrinterToFile printer = new PrinterToFile(FILE_NAME);
            printer.printClockWise(TABLE);
            printer.closePrinter();

            File file = new File(FILE_NAME);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String result = reader.readLine();

            reader.close();
            fileReader.close();
            file.delete();
            return result;
        } catch (IOException e){
            System.out.print("Couldn't get string with printed data");
            return null;
        }
    }

    /**
     * makes string with printing table counter clock-wise to file
     * @return string, which was made with table
     */
    private String getFileResultCounterClockWise() {
        try {
            PrinterToFile printer = new PrinterToFile(FILE_NAME);
            printer.printCounterClockWise(TABLE);
            printer.closePrinter();

            File file = new File(FILE_NAME);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String result = reader.readLine();

            reader.close();
            fileReader.close();
            file.delete();
            printer.closePrinter();
            return result;
        } catch (IOException e){
            System.out.print("Couldn't get string with printed data");
            return null;
        }
    }

    /**
     * checks correct printing to console on clock-wise direction
     */
    @Test
    public void printerToConsoleClockWiseTest(){
        try {
            String realClockWise = getConsoleResultClockWise();
            assertTrue(realClockWise.equals(EXPECTED_CLOCK_WISE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct printing to console on counter clock-wise direction
     */
    @Test
    public void printerToConsoleCounterClockWiseTest(){
        try {
            String realCounterClockWise = getConsoleResultCounterClockWise();
            assertTrue(realCounterClockWise.equals(EXPECTED_COUNTER_CLOCK_WISE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct printing to file on clock-wise direction
     */
    @Test
    public void printerToFileClockWiseTest(){
        try{
            String realClockWise = getFileResultClockWise();
            assertTrue(realClockWise.equals(EXPECTED_CLOCK_WISE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct printing to file on counter clock-wise direction
     */
    @Test
    public void printerToFileCounterClockWiseTest(){
        try{
            String realClockWise = getFileResultCounterClockWise();
            assertTrue(realClockWise.equals(EXPECTED_COUNTER_CLOCK_WISE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}