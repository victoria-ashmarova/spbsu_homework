public interface Sorter {
    /**
     * Method, which get array from console
     * @return
     */
    int[] getArray();

    /**
     * Method, which sorts array with some algorithm
     * @param array
     */
    void sort(int[] array);

    /**
     * Method, which prints sortes array to console
     * @param array to print
     */
    void print(int[] array);
}
