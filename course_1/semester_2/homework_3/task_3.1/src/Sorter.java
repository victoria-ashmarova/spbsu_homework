public interface Sorter {
    /**
     * Method, which sorts array with some algorithm
     * @throws NoArrayException
     * @param array
     */
    void sort(int[] array) throws NoArrayException;

    /**
     * Method, which prints sorted array to console
     * @throws NoArrayException
     * @param array to print
     */
    void print(int[] array) throws NoArrayException;
}
