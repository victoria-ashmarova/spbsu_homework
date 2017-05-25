import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contains realization of methods to print table to file.
 * @param <T> is type of data to print
 */
public class PrinterToFile<T> extends AbstractPrinter<T> implements Printer<T> {
    /**
     * to write to file
     */
    FileWriter printer = null;

    PrinterToFile(String fileName) throws IOException {
        this.printer = new FileWriter(fileName);
    }


    @Override
    protected void write(T toWrite) throws IOException {
        printer.write(toWrite + " ");
    }

    /**
     * closes file
     */
    public void closePrinter(){
        try {
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
