package ashmarova.task_2_4_2.list;

/**
 * Class contains functional of single linked ashmarova.task_2_4_2.list
 * @param <T> is type of elements
 */
public class SingleLinkedList <T> extends List<T>{
    /**
     * Add element to place in ashmarova.task_2_4_2.list
     * @param index is future index of added element
     * @param value added element
     * @throws ValueIsInListException if there must be one element with this value
     * @throws IncorrectIndexException when index of value to add is incorrect
     */
    @Override
    public void add(int index, T value) throws IncorrectIndexException, ValueIsInListException {
        if (index < 1 || index > size + 1){
            throw new IncorrectIndexException();
        }
        if (index == 1){
            if (size < 1){
                first = new Element(value, null);
            } else {
                first = new Element(value, first);
            }
            size++;
            return;
        }
        int i = 1;
        Element temp = first;
        while (i < (index - 1) && temp.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = new Element(value, temp.next);
        size++;
    }

    /**
     * Deletes value from index
     * @param index is the index of index with element to delete
     * @throws IncorrectIndexException when index of value to return is incorrect
     */
    @Override
    public void removeFromIndex(int index) throws IncorrectIndexException{
        if (index < 1 || index > size){
            throw new IncorrectIndexException();
        }
        if (index == 1){
            first = first.next;
            size--;
            return;
        }
        int i = 2;
        Element temp = first;
        while (i < index && temp.next.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    /**
     * Checks if value is  in ashmarova.task_2_4_2.list
     * @param value to search in ashmarova.task_2_4_2.list
     * @return true if value is in ashmarova.task_2_4_2.list
     */
    @Override
    public boolean isInList(T value) {
        if (size < 1){  return false;}
        Element temp = first;
        while (temp != null && !value.equals(temp.value)){
            temp = temp.next;
        }
        return temp != null;
    }

    /**
     * Gets the first number of place of the element in ashmarova.task_2_4_2.list
     * @param value is element to search
     * @return index of place with element
     * @throws NoValueInListException when there is no value with index to return
     */
    @Override
    public int searchIndex(T value) throws NoValueInListException {
        if (size < 1){
            throw new NoValueInListException();
        }
        int i = 1;
        Element temp = first;
        while (temp != null && !value.equals(temp.value)){
            i++;
            temp = temp.next;
        }
        if (temp == null){
            throw new NoValueInListException();
        }
        return i;
    }

    /**
     * Gets value from number of index
     * @param index is number of index to get element
     * @return element from this index
     * @throws IncorrectIndexException when index of value to return is incorrect
     */
    @Override
    public T getFromIndex(int index) throws IncorrectIndexException {
        if (index < 1 || index > size){
            throw new IncorrectIndexException();
        }
        T toReturn;
        if (index == 1){
            toReturn = first.value;
        } else {
            int i = 1;
            Element temp = first;
            while (i < index && temp.next != null){
                i++;
                temp = temp.next;
            }
            toReturn = temp.value;
        }
        return toReturn;
    }

    /**
     * Deletes value from it's first location
     * @param value is element to delete
     * @throws NoValueInListException when there is no value to return
     * @throws IncorrectIndexException
     */
    @Override
    public void removeFromValue(T value) throws NoValueInListException, IncorrectIndexException {
        int index = searchIndex(value);
        removeFromIndex(index);
    }
}

