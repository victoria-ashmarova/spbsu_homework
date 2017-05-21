public interface Stack<SomeType> {
    /** add value to stack */
    void push(SomeType value);

    /** get value from stack pop */
    SomeType pop() throws EmptyStackException;

    /** get size of stack*/
    int getSize();
}
