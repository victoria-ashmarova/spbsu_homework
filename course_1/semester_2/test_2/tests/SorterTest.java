import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class contains tests of generalized bubble sort for some types of objects
 */
public class SorterTest {
    /**
     * checks correct sort of Integer elements
     */
    @Test
    public void IntegerSortTest() {
        Integer[] toSort = {0, 916, 42, -5, 38};
        Integer[] expected = {-5, 0, 38, 42, 916};
        Sorter.sort(toSort, Integer::compareTo);
        assertArrayEquals(expected, toSort);
    }

    /**
     * checks correct sort of Character elements
     */
    @Test
    public void CharacterSortTest() {
        Character[] toSort = {'z', '0', '+', 's', 'a'};
        Character[] expected = {'+', '0', 'a', 's', 'z'};
        Sorter.sort(toSort, Character::compareTo);
        assertArrayEquals(expected, toSort);
    }

    /**
     * checks correct sort of string elements
     */
    @Test
    public void StringSorter() {
        String[] toSort = {"aaa", "aa", "b", "aba"};
        String[] expected = {"aa", "aaa", "aba", "b"};
        Sorter.sort(toSort, String::compareTo);
        assertArrayEquals(expected, toSort);
    }

    /**
     * checks correct sort of elements class NameAge
     */
    @Test
    public void NameAdeSorter() {
        NameAge first = new NameAge(7, "Kirill");
        NameAge second = new NameAge(7, "Alina");
        NameAge third = new NameAge(8, "Ola");
        NameAge toSort[] = {third, first, second};
        NameAge expected[] = {second, first, third};
        Sorter.sort(toSort, new NameAgeComparator());
        assertArrayEquals(expected, toSort);
    }
}