import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TreeTest {
    private Tree<Integer> createTree(Integer[] params) {
        Tree<Integer> tree = new Tree<Integer>();
        for (Integer element : params) {
            tree.add(element);
        }
        return tree;
    }

    @Test
    public void creationEmptyTreeTest() {
        Tree<Integer> tree = new Tree<Integer>();
        assertEquals(0, tree.getSize());
    }

    @Test
    public void additionAndSearchOneElementTest() {
        Tree<Integer> tree = new Tree<Integer>();
        Integer number = 33;
        tree.add(number);
        assertEquals(1, tree.getSize());
        assertTrue(tree.search(number));
    }

    @Test
    public void additionAndRemovalOneElementTest() {
        Tree<Integer> tree = new Tree<Integer>();
        Integer number = 33;
        tree.add(number);
        tree.remove(number);
        assertEquals(0, tree.getSize());
        assertFalse(tree.search(number));
    }

    @Test
    public void additionOfElementsTest() {
        Integer[] elements = {3, 4, 5};
        Integer notAddedElement = 6;
        Tree<Integer> tree = createTree(elements);
        assertEquals(3, tree.getSize());
        assertTrue(tree.search(3));
        assertTrue(tree.search(4));
        assertTrue(tree.search(5));
        assertFalse(tree.search(notAddedElement));
    }

    @Test
    public void removalRootTest() {
        Integer[] elements = {3, 4, 5, 2};
        Tree<Integer> tree = createTree(elements);
        tree.remove(3);
        assertEquals(3, tree.getSize());
        assertFalse(tree.search(3));
    }

    @Test
    public void removalLeafTest() {
        Integer[] elements = {3, 4, 5};
        Tree<Integer> tree = createTree(elements);
        tree.remove(5);
        assertEquals(2, tree.getSize());
        assertFalse(tree.search(5));
    }

    @Test
    public void removalNodeWithChildrenTest() {
        Integer[] elements = {2, 4, 3, 5};
        Tree<Integer> tree = createTree(elements);
        tree.remove(4);
        assertEquals(3, tree.getSize());
        assertFalse(tree.search(4));
    }

    @Test
    public void removalNodeWithOlyRightChild() {
        Integer[] elements = {3, 4, 5};
        Tree<Integer> tree = createTree(elements);
        tree.remove(4);
        assertEquals(2, tree.getSize());
        assertFalse(tree.search(4));
    }

    @Test
    public void removalNodeWithOlyLeftChild() {
        Integer[] elements = {3, 2, 1};
        Tree<Integer> tree = createTree(elements);
        tree.remove(2);
        assertEquals(2, tree.getSize());
        assertFalse(tree.search(2));
    }

    @Test
    public void additionTwoSimilarElementsTest() {
        Integer[] elements = {21, 21};
        Tree<Integer> tree = createTree(elements);
        assertTrue(tree.getSize() == 1);
    }


    @Test
    public void iteratorCreationForEmptyTreeTest() {
        Tree<Integer> tree = new Tree<Integer>();
        Iterator<Integer> treeIterator = tree.iterator();
        assertFalse(treeIterator.hasNext());
    }

    @Test
    public void forEachTest() {
        Integer[] elements = {5, 3, 7, 4, 2, 1, 9, 8};
        Tree<Integer> tree = createTree(elements);
        Iterator<Integer> iterator = tree.iterator();
        Integer last = iterator.next() - 1;
        for (Integer element : tree) {
            assertTrue(last < element);
            last = element;
        }
    }
}