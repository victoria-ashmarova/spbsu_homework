import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithArrayTest {
    /**
     * Checks if size of new stack is 0
     */
    @Test
    public void getInformationAfterStackCreationSizeStackTest(){
        StackWithArray stack = new StackWithArray();
        assertEquals(0, stack.getSize());
    }

    /**
     * Checks if size of array with stack is not 0
     */
    @Test
    public void getInformationAfterStackCreationSizeArrayTest(){
        StackWithArray stack = new StackWithArray();
        assertNotEquals(0, stack.sizeArray);
    }

    /**
     * Try to get value from empty stack
     * @throws EmptyStackException
     */
    @Test
    public void getFromEmptyStackTest() throws EmptyStackException{
        StackWithArray stack = new StackWithArray();
        try {
            Object objectFromEmptyStack = stack.pop();
        } catch (EmptyStackException e) {
            e.toString();
        }
    }

    /**
     * Checks that the size of stack is 1 after addition 1 element
     * @throws EmptyStackException
     */
    @Test
    public void pushAndPopOneElementGetSizeTest() throws EmptyStackException{
        StackWithArray stack = new StackWithArray();
        stack.push(2);
        assertEquals(1, stack.getSize());
    }

    /**
     * Checks equals of pushed and poped elements
     * @throws EmptyStackException
     */
    @Test
    public void pushAndPopOneElementGetElementTest() throws EmptyStackException{
        StackWithArray stack = new StackWithArray();
        stack.push(2);
        assertEquals(2, stack.pop());
    }

    /**
     * Checks making more size of array with stack
     */
    @Test
    public void checkBehaviourOfFullArray(){
        StackWithArray stack = new StackWithArray();
        int amountOfElements = stack.sizeArray;
        for (int i = 0; i <= amountOfElements ; i++){
            stack.push(i);
        }
        assertEquals(20, stack.sizeArray);
    }
}