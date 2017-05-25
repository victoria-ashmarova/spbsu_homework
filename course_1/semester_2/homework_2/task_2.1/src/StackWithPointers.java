/**
 * Class realizes stack using array
 * @param <T> if type of data
 */
public class StackWithPointers<T> implements Stack<T>{
    private Element top = null;
    private int size = 0;

    /**
     * pushes value to stack
     * @param value is value to push
     */
    @Override
    public void push(T value) {
        Element newTop = new Element(value, top);
        top = newTop;
        size++;
    }

    /**
     * gets top value from stack
     * @return top element of stack
     * @throws EmptyStackException when stack is empty
     */
    @Override
    public T pop() throws EmptyStackException {
        if (size < 1) {
            throw new EmptyStackException();
        }

        T toReturn = top.value;
        top = top.next;
        size--;
        return toReturn;
    }

    /**
     * gets amount of elements in stack
     * @return amount of elements is stack
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Class contains some value and pointer to next element
     */
    class Element{
        T value;
        Element next;

        Element(T value, Element next){
            this.value = value;
            this.next = next;
        }
    }
}
