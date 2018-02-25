import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Class contains tests for bunch sets.
 */
public class BunchSetTest {
    /**
     * creates set with integer one to four elements.
     * @return set
     */
    private BunchSet<Integer> bunchSetOneToFour(){
        BunchSet<Integer> bunch = new BunchSet<>();
        int size = 4;
        for (int i = 1; i <= size; i++) {
            bunch.add(i);
        }
        return bunch;
    }

    /**
     * checks correct work of size method.
     */
    @Test
    public void sizeTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        int expectedSize = 4;
        assertEquals(expectedSize, bunch.size());
    }

    /**
     * checks correct work of method of checking to empty, when bunch is empty.
     */
    @Test
    public void isEmptyPositiveTest(){
        Set<Integer> bunch = new BunchSet<>();
        assertTrue(bunch.isEmpty());
    }

    /**
     * checks correct work of method of checking to empty, when bunch is not empty.
     */
    @Test
    public void isEmptyNegativeTest(){
        Set<Integer> bunch = new BunchSet<>();
        bunch.add(14);
        assertFalse(bunch.isEmpty());
    }

    /**
     * checks correct work of addition element, when there is no such element in set.
     */
    @Test
    public void additionPositiveTest(){
        Set<Integer> bunch = new BunchSet<>();
        assertTrue(bunch.add(42));
    }

    /**
     * checks correct work of addition collection of elements, when there arw not some elements of collection in set.
     * ArrayList is using as collection to add
     */
    @Test
    public void addAllPositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        assertTrue(bunch.addAll(list));
    }

    /**
     * checks correct work of addition collection of elements, when there are elements of collection in set.
     * ArrayList is using as collection to add
     */
    @Test
    public void addAllNegativeTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertFalse(bunch.addAll(list));
    }

    /**
     * checks correct work of addition element, when there is such element in set.
     */
    @Test
    public void additionNegativeTest(){
        Set<Integer> bunch = new BunchSet<>();
        bunch.add(42);
        assertFalse(bunch.add(42));
    }

    /**
     * Checks correct convert to array.
     */
    @Test
    public void toArrayTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        Integer expectedArray[] = {1, 2, 3, 4};
        assertArrayEquals(expectedArray, bunch.toArray());
    }

    /**
     * checks correct work of method, which checks is element in set, when it is there.
     */
    @Test
    public void containsPositiveTest(){
        Set<Integer> bunch = new BunchSet<>();
        bunch.add(42);
        assertTrue(bunch.contains(42));
    }

    /**
     * checks correct work of method, which checks is element in set, when it is not there.
     */
    @Test
    public void containsNegativeTest(){
        Set<Integer> bunch = new BunchSet<>();
        bunch.add(42);
        assertFalse(bunch.contains(24));
    }

    /**
     * checks correct work of method, which checks is collection of element in set, when all of elements are there.
     * ArrayList is using as collection to check
     */
    @Test
    public void containsAllPositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertTrue(bunch.containsAll(list));
    }

    /**
     * checks correct work of method, which checks is collection of element in set, when not all of elements are there.
     * ArrayList is using as collection to check
     */
    @Test
    public void containsAllNegativeTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        assertFalse(bunch.containsAll(list));
    }

    /**
     * checks correct work of removal method, when there is element to remove.
     */
    @Test
    public void removePositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        assertTrue(bunch.remove(1));
    }

    /**
     * checks correct work of removal method, when there is not element to remove.
     */
    @Test
    public void removeNegativeTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        assertFalse(bunch.remove(6));
    }

    /**
     * checks removal of collection method, when there are all elements of collection in set.
     * Array List is using as collection to remove
     */
    @Test
    public void removeAllFirstPositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertTrue(bunch.removeAll(list));
    }

    /**
     * checks removal of collection method, when there are some elements of collection in set.
     * Array List is using as collection to remove
     */
    @Test
    public void removeAllSecondPositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        assertTrue(bunch.removeAll(list));
    }

    /**
     * checks removal of collection method, when there are no elements of collection in set.
     * Array List is using as collection to remove
     */
    @Test
    public void removeAllNegativeTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        assertFalse(bunch.removeAll(list));
    }

    /**
     * checks retain with collection method, when there are all elements of collection in set.
     * Array List is using as collection to retain
     */
    @Test
    public void retainAllFirstPositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertTrue(bunch.retainAll(list));
    }

    /**
     * checks removal of collection method, when there are some elements of collection in set.
     * Array List is using as collection to retain
     */
    @Test
    public void retainAllSecondPositiveTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        assertTrue(bunch.retainAll(list));
    }

    /**
     * checks removal of collection method, when there are no elements of collection in set.
     * Array List is using as collection to remove
     */
    @Test
    public void retainAllNegativeTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        assertTrue(bunch.retainAll(list));
        assertTrue(bunch.isEmpty());
    }

    /**
     * Checks correct clearing of set.
     */
    @Test
    public void clearTest(){
        Set<Integer> bunch = bunchSetOneToFour();
        assertFalse(bunch.isEmpty());
        bunch.clear();
        assertTrue(bunch.isEmpty());
    }
}