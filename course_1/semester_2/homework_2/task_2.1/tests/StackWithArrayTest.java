import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithArrayTest {
    @Test
    public void getInformationAfterStackCreationTest(){
        StackWithArray stack = new StackWithArray();
        assertEquals(0, stack.getSize());
        assertNotEquals(0, stack.sizeArray);
        assertEquals(10, stack.sizeArray);
    }

    @Test
    public void getFromEmptyStack() throws EmptyStackException{
        StackWithArray stack = new StackWithArray();
        try {
            Object objectFromEmptyStack = stack.pop();
        } catch (EmptyStackException e) {
            e.toString();
        }
    }

    @Test
    public void pushAndPopOneElement() throws EmptyStackException{
        StackWithArray stack = new StackWithArray();
        stack.push(2);
        assertEquals(1, stack.getSize());
        assertEquals(2, stack.pop());
        assertEquals(0, stack.getSize());
    }

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