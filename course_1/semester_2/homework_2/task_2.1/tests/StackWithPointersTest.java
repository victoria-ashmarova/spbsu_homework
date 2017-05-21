import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithPointersTest {
    /**
     * Checks if size of new stack is 0.
     */
    @Test
    public void getInformationAfterStackCreationTest(){
        StackWithPointers stack = new StackWithPointers();
        assertEquals(0, stack.getSize());
    }

    /**
     * Checks equals of last input and first output elements.
     */
    @Test
    public void additionAndPopOneElementTest(){
        Stack<Integer> stack = new StackWithPointers<>();
        stack.push(1);
        stack.push(2);
        try {
            assertEquals((Integer)2, stack.pop());
        } catch (EmptyStackException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Checks equals of first input and last output elements.
     */
    @Test
    public void additionAndPopTwoElementsTest(){
        Stack<Integer> stack = new StackWithPointers<>();
        stack.push(1);
        stack.push(2);
        try {
            stack.pop();
            assertEquals((Integer)1, stack.pop());
        } catch (EmptyStackException e){
            e.printStackTrace();
            e.message();
        }
    }

    /**
     * Tries to get element from empty stack.
     */
    @Test
    public void getFromEmptyStack(){
        StackWithPointers stack = new StackWithPointers();
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
            e.message();
            assertEquals(0, stack.getSize());
        }
    }
}