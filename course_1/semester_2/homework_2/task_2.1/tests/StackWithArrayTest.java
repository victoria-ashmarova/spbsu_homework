import ashmarova.task_2_2_1.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithArrayTest {
    /**
     * Checks if size of new stack is 0
     */
    @Test
    public void getInformationAfterStackCreationSizeStackTest(){
        Stack<Integer> stack = new StackWithArray<>();
        assertEquals(0, stack.getSize());
    }

    /**
     * Try to get value from empty stack
     */
    @Test
    public void getFromEmptyStackTest(){
        Stack<Integer> stack = new StackWithArray<>();
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
            assertEquals(0, stack.getSize());
        }
    }

    /**
     * Checks that the size of stack is 1 after addition 1 element
     */
    @Test
    public void pushAndPopOneElementGetSizeTest(){
        Stack<Integer> stack = new StackWithArray<>();
        stack.push(2);
        assertEquals(1, stack.getSize());
    }

    /**
     * Checks equals of pushed and poped elements
     */
    @Test
    public void pushAndPopOneElementGetElementTest(){
        try {
            StackWithArray stack = new StackWithArray();
            stack.push(2);
            assertEquals(2, stack.pop());
        } catch (EmptyStackException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks making more size of array with stack
     */
    @Test
    public void checkBehaviourOfFullArray(){
        Stack<Integer> stack = new StackWithArray<>();
        int amountOfElements = stack.getSize();
        for (int i = 0; i <= amountOfElements ; i++){
            stack.push(i);
        }
        assertEquals(20, stack.getSize());
    }
}