public class DoubleLinkedList extends List {
    ElementOfDoubleLinkedList first;

    /**
     * Class contains pointers to last and to next elements
     */
    protected class ElementOfDoubleLinkedList extends List.Element{
        ElementOfDoubleLinkedList next;
        ElementOfDoubleLinkedList prev;
        ElementOfDoubleLinkedList(Object value, ElementOfDoubleLinkedList next, ElementOfDoubleLinkedList prev){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(int place, Object value) throws PlaceException{
        if (place < 1 || place > size + 1){
            throw new PlaceException();
        }
        if (place == 1){
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
        while (i < (place - 1) && temp.next != null){
            i++;
            temp = temp.next;
        }
        temp.next = new ElementOfDoubleLinkedList(value, temp.next, temp);
        temp.next.next.prev = temp.next;
        size++;
    }

    @Override
    public void removeFromPlace(int place) throws PlaceException {
        if (place < 1 || place > size){
            throw new PlaceException();
        }
        if (place == 1){
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
        while (i < place - 1 && temp.next.next != null){
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
    public int searchPlace(Object value) throws NoValueException {
        if (size < 1){
            throw new NoValueException();
        }
        int i = 1;
        ElementOfDoubleLinkedList temp = first;
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
            ElementOfDoubleLinkedList temp = first;
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
