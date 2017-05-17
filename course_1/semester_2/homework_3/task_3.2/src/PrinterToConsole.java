public class PrinterToConsole extends AbstractPrinter implements Printer{
    @Override
    protected void write(Object toWrite) {
        System.out.print(toWrite + " ");
    }
}
