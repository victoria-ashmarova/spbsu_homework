public interface ListInterface {
    /**
     * Add element to place in list
     * @param place future index of added element
     * @param value added element
     * @throws PlaceException
     */
    void add(int place, Object value) throws PlaceException;

    /**
     * Gets the first number of place of the element in list
     * @param value is element to search
     * @return index of place with element
     * @throws NoValueException
     */
    int searchPlace(Object value) throws NoValueException;

    /**
     * Gets value from number of place
     * @param place is number of place to get element
     * @return element from this place
     * @throws PlaceException
     */
    Object getFromPlace(int place) throws PlaceException;

    /**
     * Deletes value from it's first location
     * @param value is element to delete
     * @throws NoValueException
     * @throws PlaceException
     */
    void removeFromValue(Object value) throws NoValueException, PlaceException;

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
}
