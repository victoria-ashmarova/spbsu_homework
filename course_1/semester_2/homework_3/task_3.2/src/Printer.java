/**
 * contains methods of printing table.
 * @param <T> is type of data to print
 */
public interface Printer<T> {
    /**
     * prints table as spiral with direction, which is set in parameter way.
     * @param table is data to print
     * @param way is direction of print
     */
    void print(T table[][], Main.howToPrint way);

    /**
     * prints table as spiral with counter-clock wise direction
     * @param table is data to print
     */
    void printCounterClockWise(T table[][]);

    /**
     * prints table as spiral with clock wise direction
     * @param table is data to print
     */
    void printClockWise(T table[][]);
}
