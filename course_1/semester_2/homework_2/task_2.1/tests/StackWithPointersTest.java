import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithPointersTest {
    @Test
    public void getInformationAfterStackCreationTest(){
        StackWithPointers stack = new StackWithPointers();
        assertNull(stack.top);
        assertEquals(0, stack.getSize());
    }

    @Test
    public void additionTest(){
        StackWithPointers stack = new StackWithPointers();
        stack.push(1);
        stack.push('a');
        try {
            assertEquals('a', stack.pop());
            assertEquals(1, stack.pop());
        } catch (EmptyStackException e){
            e.toString();
        }
    }

    @Test
    public void getFromEmptyStack(){
        StackWithPointers stack = new StackWithPointers();
        try {
            Object objectFromEmptyStack = stack.pop();
        } catch (EmptyStackException e) {
            e.toString();
        }
    }

    @Test
    public void getFromNotEmptyStack(){
        StackWithPointers stack = new StackWithPointers();
        try {
            stack.push(1);
            Object a = stack.pop();
            assertEquals(1, a);
        } catch (EmptyStackException e) {
            e.toString();
        }
    }

    @Test
    public void getPeekNotEmptyStack(){
        StackWithPointers stack = new StackWithPointers();
        stack.push(1);
        try {
            Object a = stack.peek();
            assertEquals(1, a);
        } catch (EmptyStackException e) {};
    }
}