import java.io.IOException;

abstract public class AbstractPrinter implements Printer{
    /**
     * searches index of table's centre
     * @param table is table to search centre
     * @return index of the table's centre
     */
    protected int getPivot(Object[][] table) {
        return table.length / 2;
    }

    /**
     * describes the way to print data to console or file
     * @param toWrite is object to print
     * @throws IOException
     */
    abstract protected void write(Object toWrite) throws IOException;

    @Override
    public void printClockWise(Object[][] table) {
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
    public void printCounterClockWise(Object table[][]) {
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
