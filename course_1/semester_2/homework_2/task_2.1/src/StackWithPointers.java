public class StackWithPointers implements Stack{
    Element top = null;
    private int size = 0;

    @Override
    public void push(Object value) {
        Element newTop = new Element(value, top);
        top = newTop;
        size++;
    }

    @Override
    public Object pop() throws EmptyStackException {
        if (size < 1) {
            throw new EmptyStackException();
        }

        Object toReturn = top.value;
        top = top.next;
        size--;
        return toReturn;
    }

    @Override
    public int getSize() {
        return size;
    }

    class Element{
        Object value;
        Element next;

        Element(Object value, Element next){
            this.value = value;
            this.next = next;
        }
    }
}
