public interface HashTableInterface <T>{
    /**
     * addes value to hash table
     * @param value is value to add
     * @throws ProblemWithElementsException when value is in table
     */
    void add(T value) throws ProblemWithElementsException;

    /**
     * removes value from hash table
     * @param value is value to remove
     * @throws ProblemWithElementsException when there is no value to remove from table
     */
    void remove(T value) throws ProblemWithElementsException;

    /**
     * checks is value is in table
     * @param value is value to check
     * @return true if value is in table
     */
    boolean isInTable(T value);
    void printStat();
}
