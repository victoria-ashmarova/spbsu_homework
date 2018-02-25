import ashmarova.task_2_4_2.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class contains tests for hash table
 */
public class HashTableTest {
    private Hasher<String> hasher = null;

    /**
     * checks, that there is no elements in created hash table
     */
    @Test
    public void hashTableCreationTest() {
        HashTable<String> table = new HashTable<>(hasher);
        assertEquals(0, table.getAmountOfElements());
    }

    /**
     * checks correct work of addition method
     * @throws UnknownProblemException in addition method
     * @throws ValueIsInTableException in addition method
     */
    @Test
    public void additionSomeDifferentEntriesTest() throws UnknownProblemException, ValueIsInTableException {
        hasher = new PolynomialStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        table.add("bbb");
        table.add("ccc");
        assertEquals(3, table.getAmountOfElements());
    }

    /**
     * checks correct work of addition method, when there is trying to add situated element
     * @throws UnknownProblemException in addition method
     * @throws ValueIsInTableException when there is trying to add situated element
     */
    @Test (expected = ValueIsInTableException.class)
    public void additionSomeEqualEntriesTest() throws UnknownProblemException, ValueIsInTableException {
        hasher = new PolynomialStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        table.add("bbb");
        table.add("ccc");
        table.add("aaa");
        assertEquals(3, table.getAmountOfElements());
    }

    /**
     * adds elements to change size of table
     * @throws UnknownProblemException in addition method
     * @throws ValueIsInTableException in addition method
     */
    @Test
    public void additionTooMushElementsTest() throws UnknownProblemException, ValueIsInTableException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        for (char toAdd = 'a'; toAdd <= 'z'; toAdd++) {
            table.add(Character.toString(toAdd));
        }
        assertEquals(26, table.getAmountOfElements());
        assertEquals(14, table.getSize());
    }

    /**
     * checks correct size with removal of element
     * @throws UnknownProblemException in addition and removal method
     * @throws ValueIsInTableException in addition method
     * @throws ValueIsNotInTableException in removal method
     */
    @Test
    public void removalElementFromTableSizeTest() throws UnknownProblemException, ValueIsInTableException, ValueIsNotInTableException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        table.add("bbb");
        table.add("ccc");
        table.remove("aaa");
        assertEquals(2, table.getAmountOfElements());
    }

    /**
     * checks absents of element after its removal
     * @throws UnknownProblemException in addition and removal method
     * @throws ValueIsNotInTableException in removal method
     * @throws ValueIsInTableException in addition method
     */
    @Test
    public void removalElementFromTableValueTest() throws UnknownProblemException, ValueIsInTableException, ValueIsNotInTableException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        table.add("bbb");
        table.add("ccc");
        table.remove("aaa");
        assertFalse(table.isInTable("aaa"));
    }

    /**
     * checks correct work of search method when value to search is in table
     * @throws ValueIsInTableException in addition method
     * @throws UnknownProblemException in method add
     */
    @Test
    public void isInTableTablePositiveTest() throws UnknownProblemException, ValueIsInTableException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        assertTrue(table.isInTable("aaa"));
    }

    /**
     * checks correct work of search method when value to search is not in table
     * @throws UnknownProblemException in method add
     * @throws ValueIsInTableException in addition method
     */
    @Test
    public void isInTableTableNegativeTest() throws UnknownProblemException, ValueIsInTableException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        assertFalse(table.isInTable("bbb"));
    }
}