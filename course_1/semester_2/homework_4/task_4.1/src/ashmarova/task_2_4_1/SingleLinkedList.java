package ashmarova.task_2_4_1;

/**
 * Class contains functional of single linked list
 * @param <T> is type of elements
 */
public class SingleLinkedList<T> extends List<T> {
    /**
     * the first element of list
     */
    private Element first;

    /**
     * Class contains list element: value and pointer to next element
     */
    private class Element {
        T value;
        Element next;
        Element(T value, Element next){
            this.value = value;
            this.next = next;
        }
    }

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

    @Override
    public boolean isInList(T value) {
        if (size < 1){  return false;}
        Element temp = first;
        while (temp != null && !value.equals(temp.value)){
            temp = temp.next;
        }
        return temp != null;
    }

    @Override
    public int searchIndex(T value) throws NoValueInListException {
        if (size < 1){
            throw new NoValueInListException();
        }
        int i = 1;
        Element temp = first;
        while (temp != null && temp.value != value){
            i++;
            temp = temp.next;
        }
        if (temp == null){
            throw new NoValueInListException();
        }
        return i;
    }

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

    @Override
    public void removeFromValue(T value) throws NoValueInListException, IncorrectIndexException {
        int index = searchIndex(value);
        removeFromIndex(index);
    }
}
