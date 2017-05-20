/**
 * Class contains functional of double linked list.
 * @param <SomeType> is type of list element
 */
public class DoubleLinkedList<SomeType> extends List <SomeType>{
    private ElementOfDoubleLinkedList first;

    /**
     * Class contains pointers to last and to next elements
     */
    protected class ElementOfDoubleLinkedList extends List.Element{
        ElementOfDoubleLinkedList next;
        ElementOfDoubleLinkedList prev;
        ElementOfDoubleLinkedList(SomeType value, ElementOfDoubleLinkedList next, ElementOfDoubleLinkedList prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(int index, SomeType value) throws IncorrectIndexException{
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        if (index == 1){
            if (size < 1){
                first = new ElementOfDoubleLinkedList(value, null, null);
            } else {
                first = new ElementOfDoubleLinkedList(value, first, null);
                first.next.prev = first;
            }
            size++;
            return;
        }
        int i = 1;
        ElementOfDoubleLinkedList temp = first;
        while (i < (index - 1) && temp.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = new ElementOfDoubleLinkedList(value, temp.next, temp);
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
        ElementOfDoubleLinkedList temp = first;
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
    public int searchIndex(SomeType value) throws AvailableValueException {
        if (size < 1){
            throw new AvailableValueException("List is empty.");
        }
        int i = 1;
        ElementOfDoubleLinkedList temp = first;
        while (temp != null && temp.getValue() != value){
            i++;
            temp = temp.next;
        }
        if (temp == null){
            throw new AvailableValueException("There is no element with this value in list.");
        }
        return i;
    }

    @Override
    public SomeType getWithIndex(int index) throws IncorrectIndexException, AvailableValueException {
        if (size < 1){
            throw new AvailableValueException("List is empty");
        }
        if (index < 1){
            throw new IncorrectIndexException("Index is less then 1.");
        }
        if (index > size + 1){
            throw new IncorrectIndexException("Index is more then size of list.");
        }
        SomeType toReturn = null;
        if (index == 1){
            toReturn = (SomeType) first.getValue();
        } else {
            int i = 1;
            ElementOfDoubleLinkedList temp = first;
            while (i < index && temp.next != null){
                i++;
                temp = temp.next;
            }
            toReturn = (SomeType) temp.getValue();
        }
        return toReturn;
    }

    @Override
    public void removeFromValue(SomeType value) throws AvailableValueException, IncorrectIndexException {
        int place = searchIndex(value);
        removeWithIndex(place);
    }
}
