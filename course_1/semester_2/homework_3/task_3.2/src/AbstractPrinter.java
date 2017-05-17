import java.io.IOException;

abstract public class AbstractPrinter implements Printer{
    @Override
    public int getPivot(Object[][] array) {
        return array.length / 2;
    }

    abstract protected void write(Object toWrite) throws IOException;

    @Override
    public void printClockWise(Object[][] array) {
        try {
            int pivot = getPivot(array);
            write(array[pivot][pivot]);
            int i = 1;
            while (i <= pivot) {
                for (int j = pivot - i + 1; j <= pivot + i; j++) {
                    write(array[pivot + i][j]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--) {
                    write(array[j][pivot + i]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--) {
                    write(array[pivot - i][j]);
                }
                for (int j = pivot - i + 1; j <= pivot + i; j++) {
                    write(array[j][pivot - i]);
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void printCounterClockWise(Object array[][]) {
        try {
            int pivot = getPivot(array);
            write(array[pivot][pivot]);
            int i = 1;
            while (i <= pivot){
                for (int j = pivot - i + 1; j <= pivot + i; j++){
                    write(array[j][pivot - i]);
                }
                for (int j = pivot - i + 1; j <= pivot + i; j++){
                    write(array[pivot + i][j]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--){
                    write(array[j][pivot + i]);
                }
                for (int j = pivot + i - 1; j >= pivot - i; j--){
                    write(array[pivot - i][j]);
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
