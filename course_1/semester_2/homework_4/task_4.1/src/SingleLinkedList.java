/**
 * Class contains functional of single linked list
 * @param <SomeType> is type of elements
 */
public class SingleLinkedList <SomeType> extends List<SomeType>{
    ElementOfSingleLinkedList first;

    /**
     * Class contains pointer to next element of list
     */
    protected class ElementOfSingleLinkedList extends List.Element{
        ElementOfSingleLinkedList next;
        ElementOfSingleLinkedList(SomeType value, ElementOfSingleLinkedList next){
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void add(int place, SomeType value) throws PlaceException, ValueIsInListException {
        if (place < 1 || place > size + 1){
            throw new PlaceException();
        }
        if (place == 1){
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
        while (i < (place - 1) && temp.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = new ElementOfSingleLinkedList(value, temp.next);
        size++;
    }

    @Override
    public void removeFromPlace(int place) throws PlaceException{
        if (place < 1 || place > size){
            throw new PlaceException();
        }
        if (place == 1){
            first = first.next;
            size--;
            return;
        }
        int i = 2;
        ElementOfSingleLinkedList temp = first;
        while (i < place && temp.next.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    @Override
    public boolean isInList(SomeType value) {
        if (size < 1){  return false;}
        ElementOfSingleLinkedList temp = first;
        while (temp != null && !value.equals(temp.value)){
            temp = temp.next;
        }
        if (temp == null){
            return false;
        }
        return true;
    }

    @Override
    public int searchPlace(SomeType value) throws NoValueInListException {
        if (size < 1){
            throw new NoValueInListException();
        }
        int i = 1;
        ElementOfSingleLinkedList temp = first;
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
    public SomeType getFromPlace(int place) throws PlaceException {
        if (place < 1 || place > size){
            throw new PlaceException();
        }
        SomeType toReturn = null;
        if (place == 1){
            toReturn = (SomeType) first.value;
        } else {
            int i = 1;
            ElementOfSingleLinkedList temp = first;
            while (i < place && temp.next != null){
                i++;
                temp = temp.next;
            }
            toReturn = (SomeType) temp.value;
        }
        return toReturn;
    }

    @Override
    public void removeFromValue(SomeType value) throws NoValueInListException, PlaceException {
        int place = searchPlace(value);
        removeFromPlace(place);
    }
}
