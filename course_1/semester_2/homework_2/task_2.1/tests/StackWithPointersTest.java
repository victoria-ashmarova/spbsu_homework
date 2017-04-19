import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithPointersTest {
    /**
     * Checks if size of new stack is 0
     */
    @Test
    public void getInformationAfterStackCreationTest(){
        StackWithPointers stack = new StackWithPointers();
        assertNull(stack.top);
        assertEquals(0, stack.getSize());
    }

    /**
     * Checks equals of last input and first output elements
     */
    @Test
    public void additionAndPopOneElementTest(){
        StackWithPointers stack = new StackWithPointers();
        stack.push(1);
        stack.push('a');
        try {
            assertEquals('a', stack.pop());
        } catch (EmptyStackException e){
            e.toString();
        }
    }

    /**
     * Checks equals of first input and last output elements
     */
    @Test
    public void additionAndPopTwoelementsTest(){
        StackWithPointers stack = new StackWithPointers();
        stack.push(1);
        stack.push('a');
        try {
            stack.pop();
            assertEquals(1, stack.pop());
        } catch (EmptyStackException e){
            e.toString();
        }
    }

    /**
     * Tries to get element from empty stack
     */
    @Test
    public void getFromEmptyStack(){
        StackWithPointers stack = new StackWithPointers();
        try {
            Object objectFromEmptyStack = stack.pop();
            assertNull(objectFromEmptyStack);
        } catch (EmptyStackException e) {
            e.toString();
        }
    }
}