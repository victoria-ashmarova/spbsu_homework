/**
 * Class contains functional of double linked list.
 * @param <T> is type of list element
 */
public class DoubleLinkedList<T> extends List<T> {
    private Element first;

    /**
     * Class contains pointers to last element and next element
     */
    protected class Element extends List.Element {
        Element prev;
        Element next;
        Element(T value, Element next, Element prev){
            super(value);
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(int index, T value) throws IncorrectIndexException{
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        if (index == 1){
            if (size < 1){
                first = new Element(value, null, null);
            } else {
                first = new Element(value, first, null);
                first.next.prev = first;
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
        temp.next = new Element(value, temp.next, temp);
        temp.next.next.prev = temp.next;
        size++;
    }

    @Override
    public void removeWithIndex(int index) throws IncorrectIndexException {
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        if (index == 1){
            if (size == 1){
                first = null;
            } else {
                first.next.prev = null;
                first = first.next;
            }
            size--;
            return;
        }
        int i = 1;
        Element temp = first;
        while (i < index - 1 && temp.next.next != null){
            i++;
            temp = temp.next;
        }
        if (temp.next.next == null){
            temp.next = null;
        } else {
            temp.next = temp.next.next;
            temp.next.prev = temp;
        }
        size--;
    }

    @Override
    public int searchIndex(T value) throws AvailableValueException {
        if (size < 1){
            throw new AvailableValueException("List is empty.");
        }
        int i = 1;
        Element temp = first;
        while (temp != null && temp.value != value){
            i++;
            temp = temp.next;
        }
        if (temp == null){
            throw new AvailableValueException("There is no element with this value in list.");
        }
        return i;
    }

    @Override
    public T getWithIndex(int index) throws IncorrectIndexException, AvailableValueException {
        if (size < 1){
            throw new AvailableValueException("List is empty");
        }
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        T toReturn = null;
        if (index == 1){
            toReturn = (T) first.value;
        } else {
            int i = 1;
            Element temp = first;
            while (i < index && temp.next != null){
                i++;
                temp = temp.next;
            }
            toReturn = (T) temp.value;
        }
        return toReturn;
    }

    @Override
    public void removeFromValue(T value) throws AvailableValueException, IncorrectIndexException {
        int place = searchIndex(value);
        removeWithIndex(place);
    }
}
