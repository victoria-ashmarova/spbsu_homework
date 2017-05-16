/**
 * Class contains functional of list without same values
 * @param <SomeType> is type of elements
 */
public class UniqueList<SomeType> extends SingleLinkedList<SomeType> {
    @Override
    public void add(int place, SomeType value) throws PlaceException, ValueIsInListException {
        if (place < 1 || place > size + 1){
            throw new PlaceException();
        }
        if (isInList(value)){
            throw new ValueIsInListException();
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
}
