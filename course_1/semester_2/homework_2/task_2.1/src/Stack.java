public interface Stack {
    /** add value to stack */
    void push(Object value);

    /** get value from stack pop */
    Object pop() throws EmptyStackException;

    /** get size of stack*/
    int getSize();
}
