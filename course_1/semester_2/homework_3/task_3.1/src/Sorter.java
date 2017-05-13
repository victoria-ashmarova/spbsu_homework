public interface Sorter {
    /**
     * Method, which get array from console
     * @throws NoArrayException
     * @return
     */
    int[] getArray() throws NoArrayException;

    /**
     * Method, which sorts array with some algorithm
     * @throws NoArrayException
     * @param array
     */
    void sort(int[] array) throws NoArrayException;

    /**
     * Method, which prints sortes array to console
     * @throws NoArrayException
     * @param array to print
     */
    void print(int[] array) throws NoArrayException;
}
