/**
 * Interface contains functional of list.
 * @param <SomeType> is type of list element
 */
public interface ListInterface <SomeType>{
    /**
     * Add element to index in list.
     * @param index future index of added element
     * @param value added element
     * @throws IncorrectIndexException when index to refer is incorrect
     */
    void add(int index, SomeType value) throws IncorrectIndexException;

    /**
     * Gets the first number of place of the element in list.
     * @param value is element to search
     * @return index of place with element
     * @throws AvailableValueException when there is no ability to get this value
     */
    int searchIndex(SomeType value) throws AvailableValueException;

    /**
     * Gets value from number of index.
     * @param index is number of index to get element
     * @return element from this index
     * @throws IncorrectIndexException when index to refer is incorrect
     * @throws AvailableValueException when there is no ability to get this value
     */
    SomeType getWithIndex(int index) throws IncorrectIndexException, AvailableValueException;

    /**
     * Deletes value from it's first location.
     * @param value is element to delete
     * @throws AvailableValueException when there is no ability to get this value
     * @throws IncorrectIndexException when index to refer is incorrect
     */
    void removeFromValue(SomeType value) throws AvailableValueException, IncorrectIndexException;

    /**
     * Deletes value from index
     * @param index is the index of index with element to delete
     * @throws IncorrectIndexException when index to refer is incorrect
     */
    void removeWithIndex(int index) throws IncorrectIndexException;

    /**
     * @return size of list
     */
    int getSize();
}
