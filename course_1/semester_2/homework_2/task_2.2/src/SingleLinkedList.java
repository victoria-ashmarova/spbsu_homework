public class SingleLinkedList extends List{
    ElementOfSingleLinkedList first;

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
    public void add(int place, Object value) throws PlaceException {
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
        int i = 1;
        ElementOfSingleLinkedList temp = first;
        while (i < place && temp.next.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    @Override
    public int searchPlace(Object value) throws NoValueException {
        if (size < 1){
            throw new NoValueException();
        }
        int i = 1;
        ElementOfSingleLinkedList temp = first;
        while (temp != null && temp.value != value){
            i++;
            temp = temp.next;
        }
        if (temp == null){
            throw new NoValueException();
        }
        return i;
    }

    @Override
    public Object getFromPlace(int place) throws PlaceException {
        if (place < 1 || place > size){
            throw new PlaceException();
        }
        Object toReturn = null;
        if (place == 1){
            toReturn = first.value;
        } else {
            int i = 1;
            ElementOfSingleLinkedList temp = first;
            while (i < place && temp.next != null){
                i++;
                temp = temp.next;
            }
            toReturn = temp.value;
        }
        return toReturn;
    }

    @Override
    public void removeFromValue(Object value) throws NoValueException, PlaceException {
        int place = searchPlace(value);
        removeFromPlace(place);
    }
}
