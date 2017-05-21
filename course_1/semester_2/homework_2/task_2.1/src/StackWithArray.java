public class StackWithArray<SomeType> implements Stack<SomeType>{
    private int sizeStack = 0;
    protected int sizeArray = 10;
    private  SomeType stack[];

    StackWithArray(){
        this.stack = (SomeType[]) new Object[sizeArray];
    }

    @Override
    public void push(SomeType value) {
        if (sizeStack >= sizeArray){
            makeArraySizeMore();
        }

        stack[sizeStack] = value;
        sizeStack++;
    }

    /** The method adds 10 cells to array with stack if the size of stack is more then the size of array. */
    private void makeArraySizeMore(){
        SomeType newStack[] = (SomeType[]) new Object[sizeArray + 10];
        for (int i = 0; i < sizeArray; i++){
            newStack[i] = stack[i];
        }
        stack = newStack;
        sizeArray = sizeArray + 10;
    }

    @Override
    public SomeType pop() throws EmptyStackException {
        if (sizeStack < 1){
            throw new EmptyStackException();
        }

        SomeType toReturn = stack[sizeStack - 1];
        stack[sizeStack - 1] = null;
        sizeStack--;
        return toReturn;
    }

    @Override
    public int getSize() {
        return sizeStack;
    }
}
