/**
 * Class contains functional of single linked list.
 * @param <T> is type of list element
 */
public class SingleLinkedList<T> extends List<T> {
    private Element first;

    /**
     * Class contains pointers to next element
     */
    protected class Element extends List<T>.Element{
        Element next;

        Element(T value, Element next) {
            super(value);
            this.next = next;
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

    @Override
    public void removeWithIndex(int index) throws IncorrectIndexException{
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        if (index == 1){
            first = first.next;
            size--;
            return;
        }
        int i = 1;
        Element temp = first;
        while (i < index && temp.next.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
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
            throw new AvailableValueException("There is no this value in list");
        }
        return i;
    }

    @Override
    public T getWithIndex(int index) throws IncorrectIndexException, AvailableValueException{
        if (size < 1){
            throw new AvailableValueException("List is empty.");
        }
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        T toReturn = null;
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

    @Override
    public void removeFromValue(T value) throws AvailableValueException, IncorrectIndexException{
        int place = searchIndex(value);
        removeWithIndex(place);
    }
}
