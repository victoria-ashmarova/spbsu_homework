import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private enum placeToPrint{CONSOLE, FILE}
    private enum howToPrint{CLOCK_WISE, COUNTER_CLOCK_WISE}

    private static howToPrint howToPrint(Scanner consoleScanner){
        try {
            System.out.print("Enter 0 to print the table counter clock wise. Enter else number to print the table clock wise: ");
            int wayOfPrint = consoleScanner.nextInt();
            return  wayOfPrint == 0 ? howToPrint.COUNTER_CLOCK_WISE : howToPrint.CLOCK_WISE;
        } catch (InputMismatchException e){
            return howToPrint.CLOCK_WISE;
        }
    }

    private static placeToPrint placeToPrint(Scanner consoleScanner){
        try {
            System.out.print("Enter 0 to print the table to console. Enter else number to print the table to file: ");
            int placeToPrint = consoleScanner.nextInt();
            return placeToPrint == 0 ? Main.placeToPrint.CONSOLE : Main.placeToPrint.FILE;
        } catch (InputMismatchException e) {
            return placeToPrint.FILE;
        }
    }

    public static String getFileName(Scanner consoleScan){
        System.out.print("Enter the name of file with array: ");
        return consoleScan.next();
    }

    public static void main(String args[]) throws FileNotFoundException {
        try {
            ArrayReader reader = new ArrayReader();
            Scanner scan = reader.getFile();
            Scanner consoleScanner = new Scanner(System.in);
            Object table[][] = reader.makeArray(scan);
            reader.fullArray(table, scan);
            scan.close();
            placeToPrint place = placeToPrint(consoleScanner);
            howToPrint way = howToPrint(consoleScanner);
            switch (place){
                case CONSOLE:{
                    PrinterToConsole printer = new PrinterToConsole();
                    if (way == howToPrint.CLOCK_WISE){
                        printer.printClockWise(table);
                    } else {
                        printer.printCounterClockWise(table);
                    }
                    break;
                }
                case FILE:{
                    PrinterToFile printer = new PrinterToFile(getFileName(consoleScanner));
                    if (way == howToPrint.CLOCK_WISE){
                        printer.printClockWise(table);
                    } else {
                        printer.printCounterClockWise(table);
                    }
                    printer.closePrinter();
                    System.out.print("Data is written to file ");
                    break;
                }
            }
        } catch (FileNotFoundException e){
            System.out.print("File not found.");
        } catch (NoTableException e){
            System.out.print("Couldn't read the table. Index of size is incorrect");
        } catch (NoSuchElementException e){
            System.out.print("Couldn't read the table. To less elements in the table");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
