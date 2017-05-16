public interface ListInterface<SomeType>{
    /**
     * Add element to place in list
     * @param place future index of added element
     * @param value added element
     * @throws PlaceException
     */
    void add(int place, SomeType value) throws PlaceException, ValueIsInListException;

    /**
     * Gets the first number of place of the element in list
     * @param value is element to search
     * @return index of place with element
     * @throws NoValueInListException
     */
    int searchPlace(SomeType value) throws NoValueInListException;

    /**
     * Gets value from number of place
     * @param place is number of place to get element
     * @return element from this place
     * @throws PlaceException
     */
    SomeType getFromPlace(int place) throws PlaceException;

    /**
     * Deletes value from it's first location
     * @param value is element to delete
     * @throws NoValueInListException
     * @throws PlaceException
     */
    void removeFromValue(SomeType value) throws NoValueInListException, PlaceException;

    /**
     * Deletes value from place
     * @param place is the index of place with element to delete
     * @throws PlaceException
     */
    void removeFromPlace(int place) throws PlaceException;

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
