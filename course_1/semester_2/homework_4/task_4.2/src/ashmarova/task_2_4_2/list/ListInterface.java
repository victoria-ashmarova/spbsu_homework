package ashmarova.task_2_4_2.list;

public interface ListInterface<T>{
    /**
     * Add element to index in ashmarova.task_2_4_2.list
     * @param index future index of added element
     * @param value added element
     * @throws ValueIsInListException if there must be one element with this value
     * @throws IncorrectIndexException when index of value to add is incorrect
     */
    void add(int index, T value) throws IncorrectIndexException, ValueIsInListException;

    /**
     * Gets the first number of place of the element in ashmarova.task_2_4_2.list
     * @param value is element to search
     * @return index of place with element
     * @throws NoValueInListException when there is no value with index to return
     */
    int searchIndex(T value) throws NoValueInListException;

    /**
     * Gets value from number of index
     * @param index is number of index to get element
     * @return element from this index
     * @throws IncorrectIndexException when index of value to return is incorrect
     */
    T getFromIndex(int index) throws IncorrectIndexException;

    /**
     * Deletes value from it's first location
     * @param value is element to delete
     * @throws NoValueInListException when there is no value to return
     * @throws IncorrectIndexException
     */
    void removeFromValue(T value) throws NoValueInListException, IncorrectIndexException;

    /**
     * Deletes value from index
     * @param index is the index of index with element to delete
     * @throws IncorrectIndexException when index of value to return is incorrect
     */
    void removeFromIndex(int index) throws IncorrectIndexException;

    /**
     * @return size of ashmarova.task_2_4_2.list
     */
    int getSize();

    /**
     * Checks if value is  in ashmarova.task_2_4_2.list
     * @param value to search in ashmarova.task_2_4_2.list
     * @return true if value is in ashmarova.task_2_4_2.list
     */
    boolean isInList(T value);
}
