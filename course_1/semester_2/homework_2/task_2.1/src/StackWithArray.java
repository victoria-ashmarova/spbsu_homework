public class StackWithArray implements Stack{
    private int sizeStack = 0;
    protected int sizeArray = 10;
    private  Object stack[] = new Object[sizeArray];

    @Override
    public void push(Object value) {
        if (sizeStack >= sizeArray){
            makeArraySizeMore();
        }

        stack[sizeStack] = value;
        sizeStack++;
    }

    /** The method adds 10 places to array with stack if the size of stack is more then the size of array */
    private void makeArraySizeMore(){
        Object newStack[] = new Object[sizeArray + 10];
        for (int i = 0; i < sizeArray; i++){
            newStack[i] = stack[i];
        }
        stack = newStack;
        sizeArray = sizeArray + 10;
    }

    @Override
    public Object pop() throws EmptyStackException {
        if (sizeStack < 1){
            throw new EmptyStackException();
        }

        Object toReturn = stack[sizeStack - 1];
        stack[sizeStack - 1] = 0;
        sizeStack--;
        return toReturn;
    }

    @Override
    public int getSize() {
        return sizeStack;
    }

    public Object peek() throws  EmptyStackException{
        if (sizeStack == 0){
            throw new EmptyStackException();
        }
        return stack[sizeStack];
    }
}
