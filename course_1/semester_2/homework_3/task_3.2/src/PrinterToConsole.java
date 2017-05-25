/**
 * Contains realization of methods to print table to console.
 * @param <T> is type of data to print
 */
public class PrinterToConsole<T> extends AbstractPrinter<T> implements Printer<T> {
    @Override
    protected void write(T toWrite) {
        System.out.print(toWrite + " ");
    }
}
