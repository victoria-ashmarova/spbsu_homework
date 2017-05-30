package ashmarova.task_2_4_2;

public interface HashTableInterface <T>{
    /**
     * addes value to hash table
     * @param value is value to add
     * @throws UnknownProblemException when value is in table
     */
    void add(T value) throws UnknownProblemException, ValueIsInTableException;

    /**
     * removes value from hash table
     * @param value is value to remove
     * @throws UnknownProblemException when there is no value to remove from table
     */
    void remove(T value) throws UnknownProblemException, ValueIsNotInTableException;

    /**
     * checks is value is in table
     * @param value is value to check
     * @return true if value is in table
     */
    boolean isInTable(T value);
    void printStat();
}
