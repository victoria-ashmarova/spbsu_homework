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
     * @throws ProblemWithElementsException in method add
     */
    @Test
    public void additionSomeDifferentEntriesTest() throws ProblemWithElementsException {
        hasher = new PolynomialStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        table.add("bbb");
        table.add("ccc");
        assertEquals(3, table.getAmountOfElements());
    }

    /**
     * checks correct work of addition method, when there is trying to add situated element
     * @throws ProblemWithElementsException when there is trying to add situated element
     */
    @Test (expected = ProblemWithElementsException.class)
    public void additionSomeEqualEntriesTest() throws ProblemWithElementsException {
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
     * @throws ProblemWithElementsException in method add
     */
    @Test
    public void additionTooMushElementsTest() throws ProblemWithElementsException {
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
     * @throws ProblemWithElementsException in method remove
     */
    @Test
    public void removalElementFromTableSizeTest() throws ProblemWithElementsException {
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
     * @throws ProblemWithElementsException in method remove
     */
    @Test
    public void removalElementFromTableValueTest() throws ProblemWithElementsException {
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
     * @throws ProblemWithElementsException in method add
     */
    @Test
    public void isInTableTablePositiveTest() throws ProblemWithElementsException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        assertTrue(table.isInTable("aaa"));
    }

    /**
     * checks correct work of search method when value to search is not in table
     * @throws ProblemWithElementsException in method add
     */
    @Test
    public void isInTableTableNegativeTest() throws ProblemWithElementsException {
        hasher = new MultiplicativeToConstantStringHasher();
        HashTable<String> table = new HashTable<>(hasher);
        table.add("aaa");
        assertFalse(table.isInTable("bbb"));
    }
}