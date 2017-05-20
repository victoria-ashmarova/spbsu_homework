/**
 * Class contains functional of single linked list.
 * @param <SomeType> is type of list element
 */
public class SingleLinkedList<SomeType> extends List<SomeType>{
    private ElementOfSingleLinkedList first;

    /**
     * Class contains pointer to next element of list
     */
    protected class ElementOfSingleLinkedList extends List.Element{
        ElementOfSingleLinkedList next;
        ElementOfSingleLinkedList(Object value, ElementOfSingleLinkedList next){
            this.value = value;
            this.next = next;
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
                first = new ElementOfSingleLinkedList(value, null);
            } else {
                first = new ElementOfSingleLinkedList(value, first);
            }
            size++;
            return;
        }
        int i = 1;
        ElementOfSingleLinkedList temp = first;
        while (i < (index - 1) && temp.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = new ElementOfSingleLinkedList(value, temp.next);
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
        ElementOfSingleLinkedList temp = first;
        while (i < index && temp.next.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    @Override
    public int searchIndex(SomeType value) throws AvailableValueException {
        if (size < 1){
            throw new AvailableValueException("List is empty.");
        }
        int i = 1;
        ElementOfSingleLinkedList temp = first;
        while (temp != null && temp.getValue() != value){
            i++;
            temp = temp.next;
        }
        if (temp == null){
            throw new AvailableValueException("There is no this value in list");
        }
        return i;
    }

    @Override
    public SomeType getWithIndex(int index) throws IncorrectIndexException, AvailableValueException{
        if (size < 1){
            throw new AvailableValueException("List is empty.");
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
            ElementOfSingleLinkedList temp = first;
            while (i < index && temp.next != null){
                i++;
                temp = temp.next;
            }
            toReturn = (SomeType) temp.getValue();
        }
        return toReturn;
    }

    @Override
    public void removeFromValue(SomeType value) throws AvailableValueException, IncorrectIndexException{
        int place = searchIndex(value);
        removeWithIndex(place);
    }
}
