import java.io.IOException;

/**
 * Contains realization of methods to print table.
 * @param <T> is type of data to print
 */
abstract public class AbstractPrinter<T> implements Printer<T> {
    @Override
    public void print(T table[][], Main.howToPrint way){
        if (way == Main.howToPrint.CLOCK_WISE){
            printClockWise(table);
        } else {
            printCounterClockWise(table);
        }
    }

    /**
     * searches index of table's centre
     * @param table is table to search centre
     * @return index of the table's centre
     */
    protected int getPivot(T[][] table) {
        return table.length / 2;
    }

    /**
     * describes the way to print data to console or file
     * @param toWrite is object to print
     * @throws IOException
     */
    abstract protected void write(T toWrite) throws IOException;

    @Override
    public void printClockWise(T[][] table) {
        try {
            int pivot = getPivot(table);
            write(table[pivot][pivot]);
            int i = 1;
            while (i <= pivot) {
                for (int j = pivot - i + 1; j <= pivot + i; j++) {
                    write(table[pivot + i][j]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--) {
                    write(table[j][pivot + i]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--) {
                    write(table[pivot - i][j]);
                }
                for (int j = pivot - i + 1; j <= pivot + i; j++) {
                    write(table[j][pivot - i]);
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void printCounterClockWise(T table[][]) {
        try {
            int pivot = getPivot(table);
            write(table[pivot][pivot]);
            int i = 1;
            while (i <= pivot){
                for (int j = pivot - i + 1; j <= pivot + i; j++){
                    write(table[j][pivot - i]);
                }
                for (int j = pivot - i + 1; j <= pivot + i; j++){
                    write(table[pivot + i][j]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--){
                    write(table[j][pivot + i]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--){
                    write(table[pivot - i][j]);
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
