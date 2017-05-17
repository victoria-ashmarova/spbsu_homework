import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class AbstractPrinterTest {
    private Object table[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private String expectedClockWise = "5 8 9 6 3 2 1 4 7 ";
    private String expectedCounterClockWise = "5 4 7 8 9 6 3 2 1 ";
    private String fileName = "file.txt";

    private String getConsoleResultClockWise() throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);

        PrintStream backup = System.out;
        System.setOut(printStream);

        PrinterToConsole printer = new PrinterToConsole();
        printer.printClockWise(table);

        System.setOut(backup);
        printStream.close();

        String result = byteStream.toString();
        return result;
    }

    private String getConsoleResultCounterClockWise() throws Exception {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);

        PrintStream backup = System.out;
        System.setOut(printStream);

        PrinterToConsole printer = new PrinterToConsole();
        printer.printCounterClockWise(table);

        System.setOut(backup);
        printStream.close();

        String result = byteStream.toString();
        return result;
    }

    private String getFileResultClockWise() throws Exception {
        PrinterToFile printer = new PrinterToFile(fileName);
        printer.printClockWise(table);
        printer.closePrinter();

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String result = reader.readLine();

        reader.close();
        fileReader.close();
        file.delete();
        return result;
    }

    private String getFileResultCounterClockWise() throws Exception {
        PrinterToFile printer = new PrinterToFile(fileName);
        printer.printCounterClockWise(table);
        printer.closePrinter();

        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String result = reader.readLine();

        reader.close();
        fileReader.close();
        file.delete();
        printer.closePrinter();
        return result;
    }


    @Test
    public void printerToConsoleClockWiseTest(){
        try {
            String realClockWise = getConsoleResultClockWise();
            assertTrue(realClockWise.equals(expectedClockWise));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printerToConsoleCounterClockWiseTest(){
        try {
            String realCounterClockWise = getConsoleResultCounterClockWise();
            assertTrue(realCounterClockWise.equals(expectedCounterClockWise));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printerToFileClockWiseTest(){
        try{
            String realClockWise = getFileResultClockWise();
            assertTrue(realClockWise.equals(expectedClockWise));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printerToFileCounterClockWiseTest(){
        try{
            String realClockWise = getFileResultCounterClockWise();
            assertTrue(realClockWise.equals(expectedCounterClockWise));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}