/**
 * Class realizes stack using array
 * @param <T> is type of data
 */
public class StackWithArray<T> implements Stack<T>{
    private int sizeStack = 0;
    protected int sizeArray = 10;
    private T stack[];

    StackWithArray(){
        this.stack = (T[]) new Object[sizeArray];
    }

    /**
     * pushes value to stack
     * @param value to push
     */
    @Override
    public void push(T value) {
        if (sizeStack >= sizeArray){
            makeArraySizeMore();
        }

        stack[sizeStack] = value;
        sizeStack++;
    }

    /** The method adds 10 cells to array with stack if the size of stack is more then the size of array. */
    private void makeArraySizeMore(){
        T newStack[] = (T[]) new Object[sizeArray + 10];
        for (int i = 0; i < sizeArray; i++){
            newStack[i] = stack[i];
        }
        stack = newStack;
        sizeArray = sizeArray + 10;
    }

    /**
     * gets top value from stack
     * @return top element of stack
     * @throws EmptyStackException when stack is empty
     */
    @Override
    public T pop() throws EmptyStackException {
        if (sizeStack < 1){
            throw new EmptyStackException();
        }

        T toReturn = stack[sizeStack - 1];
        stack[sizeStack - 1] = null;
        sizeStack--;
        return toReturn;
    }

    /**
     * gets amount of elements in stack
     * @return amount of elements in stack
     */
    @Override
    public int getSize() {
        return sizeStack;
    }
}
