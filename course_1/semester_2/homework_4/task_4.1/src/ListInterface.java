public interface ListInterface<SomeType>{
    /**
     * Add element to place in list
     * @param place future index of added element
     * @param value added element
     * @throws ValueIsInListException if there must be one element with this value
     * @throws IncorrectIndexException when index of value to add is incorrect
     */
    void add(int place, SomeType value) throws IncorrectIndexException, ValueIsInListException;

    /**
     * Gets the first number of place of the element in list
     * @param value is element to search
     * @return index of place with element
     * @throws NoValueInListException when there is no value with index to return
     */
    int searchIndex(SomeType value) throws NoValueInListException;

    /**
     * Gets value from number of index
     * @param index is number of index to get element
     * @return element from this index
     * @throws IncorrectIndexException when index of value to return is incorrect
     */
    SomeType getFromIndex(int index) throws IncorrectIndexException;

    /**
     * Deletes value from it's first location
     * @param value is element to delete
     * @throws NoValueInListException when there is no value to return
     * @throws IncorrectIndexException
     */
    void removeFromValue(SomeType value) throws NoValueInListException, IncorrectIndexException;

    /**
     * Deletes value from index
     * @param index is the index of index with element to delete
     * @throws IncorrectIndexException when index of value to return is incorrect
     */
    void removeFromIndex(int index) throws IncorrectIndexException;

    /**
     * @return size of list
     */
    int getSize();

    /**
     * Checks if value is  in list
     * @param value to search in list
     * @return true if value is in list
     */
    boolean isInList(SomeType value);
}
