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
     */
    @Test
    public void treeWithOneActionTest(){
        final String TREE = "(+ 2 4)";
        final String EXPECTED_TREE = "(+ 2  4 )";
        try {
            assertTrue(EXPECTED_TREE.equals(getStringOfTree(TREE)));
        } catch (IncorrectTreeException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct value of tree with one action
     */
    @Test
    public void treeWithOneActionResultTest(){
        final String TREE = "(+ 2 4)";
        final int RESULT = 6;
        try {
            assertEquals(RESULT, getValueOfTree(TREE));
        } catch (IncorrectTreeException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct printing of tree with two actions
     */
    @Test
    public void treeWithTwoActionsTest(){
        final String TREE = "(*(+ 2 4)3";
        final String EXPECTED_TREE = "(*(+ 2  4 ) 3 )";
        try {
            assertTrue(EXPECTED_TREE.equals(getStringOfTree(TREE)));
        } catch (IncorrectTreeException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct value of tree with two actions
     */
    @Test
    public void treeWithTwoActionsResultTest(){
        final String TREE = "(*(+ 2 4)3";
        final int RESULT = 18;
        try {
            assertEquals(RESULT, getValueOfTree(TREE));
        } catch (IncorrectTreeException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct printing of tree with all kinds of actions
     */
    @Test
    public void treeWithALotOfActionsTest(){
        final String TREE = "(+ (* (- 19 5) 5)(/ 28 (* 2 7)))";
        final String EXPECTED_TREE = "(+(*(- 19  5 ) 5 )(/ 28 (* 2  7 )))";
        try {
            assertTrue(EXPECTED_TREE.equals(getStringOfTree(TREE)));
        } catch (IncorrectTreeException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks correct value of tree with all kinds of actions
     */
    @Test
    public void treeWithALotOfActionsResultTest(){
        final String TREE = "(+ (* (- 19 5) 5)(/ 28 (* 2 7)))";
        final int RESULT = 72;
        try {
            assertEquals(RESULT, getValueOfTree(TREE));
        } catch (IncorrectTreeException e) {
            e.printStackTrace();
        }
    }
}