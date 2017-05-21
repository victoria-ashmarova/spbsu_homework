public class StackWithPointers<SomeType> implements Stack<SomeType>{
    private Element top = null;
    private int size = 0;

    @Override
    public void push(SomeType value) {
        Element newTop = new Element(value, top);
        top = newTop;
        size++;
    }

    @Override
    public SomeType pop() throws EmptyStackException {
        if (size < 1) {
            throw new EmptyStackException();
        }

        SomeType toReturn = top.value;
        top = top.next;
        size--;
        return toReturn;
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * Class contains some value and pointer to next element
     */
    class Element{
        SomeType value;
        Element next;

        Element(SomeType value, Element next){
            this.value = value;
            this.next = next;
        }
    }
}
