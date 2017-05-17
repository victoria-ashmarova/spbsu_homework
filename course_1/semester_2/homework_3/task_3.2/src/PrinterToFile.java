import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PrinterToFile  extends AbstractPrinter implements Printer {
    /**
     * to write to file
     */
    FileWriter printer = null;

    PrinterToFile(String fileName) throws IOException {
        this.printer = new FileWriter(fileName);
    }

    @Override
    protected void write(Object toWrite) throws IOException {
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
