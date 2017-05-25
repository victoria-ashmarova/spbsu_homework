public interface Stack<T> {
    /** pushes value to stack */
    void push(T value);

    /** get value from stack pop */
    T pop() throws EmptyStackException;

    /** get size of stack*/
    int getSize();
}
