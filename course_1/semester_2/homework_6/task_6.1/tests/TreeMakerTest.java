/**
 * Class contains tests of work with tree
 */

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TreeMakerTest {
    private Scanner scan;

    /**
     * makes tree with string with it
     * @param tree is string with tree
     * @return
     */
    private String getStringOfTree(String tree) throws IncorrectTreeException{
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteStream);

        PrintStream backup = System.out;
        System.setOut(printStream);

        scan = new Scanner(tree);
        TreeMaker tMaker = new TreeMaker(scan);

        try {
            tMaker.makeTree();
            tMaker.print();
            System.setOut(backup);
            String result = byteStream.toString();
            return result;

        } catch (IncorrectTreeException e) {
            throw new IncorrectTreeException();
        } finally {
            printStream.close();
            scan.close();
        }
    }

    /**
     * gets value of tree
     * @param tree to get value
     * @return value of tree
     * @throws IncorrectTreeException when there is no ability to calculate tree
     */
    private int getValueOfTree(String tree) throws IncorrectTreeException{
        scan = new Scanner(tree);
        TreeMaker tMaker = new TreeMaker(scan);
        try {
            tMaker.makeTree();
            int result = tMaker.getValue();
            return result;
        } catch (IncorrectTreeException e) {
            throw new IncorrectTreeException();
        } finally {
            scan.close();
        }
    }

    /**
     * check correct printing tree with one action
     * @throws IncorrectTreeException when there is no ability to get value of tree
     */
    @Test
    public void treeWithOneActionTest() throws IncorrectTreeException {
        final String TREE = "(+ 2 4)";
        final String EXPECTED_TREE = "(+ 2  4 )";
        assertTrue(EXPECTED_TREE.equals(getStringOfTree(TREE)));
    }

    /**
     * checks correct value of tree with one action
     * @throws IncorrectTreeException when there is no ability to get value of tree
     */
    @Test
    public void treeWithOneActionResultTest() throws IncorrectTreeException {
        final String TREE = "(+ 2 4)";
        final int RESULT = 6;
        assertEquals(RESULT, getValueOfTree(TREE));
    }

    /**
     * checks correct printing of tree with two actions
     * @throws IncorrectTreeException when there is no ability to get value of tree
     */
    @Test
    public void treeWithTwoActionsTest() throws IncorrectTreeException {
        final String TREE = "(*(+ 2 4)3";
        final String EXPECTED_TREE = "(*(+ 2  4 ) 3 )";
        assertTrue(EXPECTED_TREE.equals(getStringOfTree(TREE)));
    }

    /**
     * checks correct value of tree with two actions
     * @throws IncorrectTreeException when there is no ability to get value of tree
     */
    @Test
    public void treeWithTwoActionsResultTest() throws IncorrectTreeException {
        final String TREE = "(*(+ 2 4)3";
        final int RESULT = 18;
        assertEquals(RESULT, getValueOfTree(TREE));
    }

    /**
     * checks correct printing of tree with all kinds of actions
     * @throws IncorrectTreeException when there is no ability to get value of tree
     */
    @Test
    public void treeWithALotOfActionsTest() throws IncorrectTreeException {
        final String TREE = "(+ (* (- 19 5) 5)(/ 28 (* 2 7)))";
        final String EXPECTED_TREE = "(+(*(- 19  5 ) 5 )(/ 28 (* 2  7 )))";
        assertTrue(EXPECTED_TREE.equals(getStringOfTree(TREE)));
    }

    /**
     * checks correct value of tree with all kinds of actions
     * @throws IncorrectTreeException when there is no ability to get value of tree
     */
    @Test
    public void treeWithALotOfActionsResultTest() throws IncorrectTreeException {
        final String TREE = "(+ (* (- 19 5) 5)(/ 28 (* 2 7)))";
        final int RESULT = 72;
        assertEquals(RESULT, getValueOfTree(TREE));
    }
}