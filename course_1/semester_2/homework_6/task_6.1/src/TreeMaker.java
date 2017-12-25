/**
 * Class, which made tree with file
 */

import java.util.Scanner;
import java.util.Stack;

public class TreeMaker {
    private AbstractNode root;
    private Scanner scan;

    TreeMaker(Scanner scan){
        this.scan = scan;
    }

    /**
     * calculates value of tree
     * @return value of tree
     * @throws IncorrectTreeException when there is no tree
     */
    public int getValue() throws IncorrectTreeException {
        try {
            return root.getValue();
        } catch (NullPointerException e){
            System.out.print("Couldn't get value of tree\n");
            throw new IncorrectTreeException();
        }
    }

    /**
     * prints tree if knows its root
     * @throws IncorrectTreeException when there is no root of tree or tree is incorrect
     */
    public void print() throws IncorrectTreeException{
        try {
            root.printAllChildren();
        } catch (NullPointerException e){
            System.out.print("Couldn't print tree\n");
            throw new IncorrectTreeException();
        }
    }

    /**
     * makes tree with input data - strings woth numbers and operations
     * @throws IncorrectTreeException when input data to make tree are incorrect
     */
    public void makeTree() throws IncorrectTreeException {
        String current;
        Stack<Action> ownersOfEmptySons = new Stack<>();
        while (scan.hasNext()){
            current = scan.next();
            if (!hasAbilityToMakeTree(current)){
                throw new IncorrectTreeException();
            }
            if (current.length() > 1 && current.charAt(0) == '('){
                current = current.substring(1);
            }
            if (isNumber(current)) {
                addNumberToTree(ownersOfEmptySons, converterToNumber(current));
            }
            if (isAction(current)){
                Action newAction = makeAction(current);
                addActionToTree(ownersOfEmptySons, newAction);
            }
            if (!isNumber(current) && !isAction(current)){
                addComplexString(ownersOfEmptySons, current);
            }
        }
        scan.close();
        if (!ownersOfEmptySons.empty()) {
            throw new IncorrectTreeException();
        }
    }

    /**
     * adds action to tree
     * @param ownersOfEmptySons is store of future parents for next nodes
     * @param toAdd is action to add to ree
     */
    private void addActionToTree(Stack<Action> ownersOfEmptySons, Action toAdd){
        if (ownersOfEmptySons.empty()) {
            root = toAdd;
            ownersOfEmptySons.push(toAdd);
            return;
        }

        Action newParent = ownersOfEmptySons.peek();
        if (newParent.leftSonIsEmpty()) {
            newParent.setLeftSon(toAdd);
            ownersOfEmptySons.push(toAdd);
            return;
        }
        if (newParent.rightSonIsEmpty()){
            newParent.setRightSon(toAdd);
            ownersOfEmptySons.pop();
            ownersOfEmptySons.push(toAdd);
            return;
        }
    }

    /**
     * creates Action, using its string identifier
     * @param whatAction is string identifier of action
     * @return object with action
     */
    private Action makeAction(String whatAction){
        Action newAction = null;
        switch (whatAction) {
            case "+": {
                newAction = new Sum();
                break;
            }
            case "-": {
                newAction
                        = new Difference();
                break;
            }
            case "*": {
                newAction = new Multiplication();
                break;
            }
            case "/": {
                newAction = new Quotient();
                break;
            }
        }
        return newAction;
    }

    /**
     * adds number value to tree
     * @param ownersOfEmptySons is store of future parents for next nodes
     * @param toAdd is value for Node Value to add
     */
    private void addNumberToTree(Stack<Action> ownersOfEmptySons, int toAdd){
        if (ownersOfEmptySons.empty()) {
            root = new Value(toAdd);
            return;
        }

        AbstractNode newValue = new Value(toAdd);
        Action newParent = ownersOfEmptySons.peek();
        if (newParent.leftSonIsEmpty()) {
            newParent.setLeftSon(newValue);
            return;
        }
        if (newParent.rightSonIsEmpty()){
            newParent.setRightSon(newValue);
            ownersOfEmptySons.pop();
            return;
        }
    }

    /**
     * adds string, where may be identifier not only one node
     * @param ownersOfEmptySons is store of future parents for next nodes
     * @param toAdd is string with identifiers of nodes
     */
    private void addComplexString(Stack<Action> ownersOfEmptySons, String toAdd){
        int indexOfLastBracket = -1;
        int indexOfLastButOneBracket = -1;
        for (int i = 0 ; i < toAdd.length(); i++){
            if (toAdd.charAt(i) == '(' || toAdd.charAt(i) == ')'){
                indexOfLastButOneBracket = indexOfLastBracket;
                indexOfLastBracket = i;
                String current = toAdd.substring(indexOfLastButOneBracket + 1, indexOfLastBracket);
                if (isNumber(current)){
                    addNumberToTree(ownersOfEmptySons, converterToNumber(current));
                }
                if (isAction(current)){
                    addActionToTree(ownersOfEmptySons, makeAction(current));
                }
            }
        }
        indexOfLastButOneBracket = indexOfLastBracket;
        indexOfLastBracket = toAdd.length();
        String current = toAdd.substring(indexOfLastButOneBracket + 1, indexOfLastBracket);
        if (isNumber(current)){
            addNumberToTree(ownersOfEmptySons, converterToNumber(current));
        }
        if (isAction(current)){
            addActionToTree(ownersOfEmptySons, makeAction(current));
        }
    }

    /**
     * checks if string identifier of number
     * @param toCheck is string identifier
     * @return true if it is
     */
    private boolean isNumber(String toCheck){
        if (toCheck.length() == 0) {
            return false;
        }
        boolean toReturn = true;
        for (int i = 0; i < toCheck.length() - 1; i++) {
            if (toCheck.charAt(i) < '0' || toCheck.charAt(i) > '9') {
                toReturn = false;
            }
        }
        char lastSymbol = toCheck.charAt(toCheck.length() - 1);
        if ((lastSymbol < '0' || lastSymbol > '9') && lastSymbol != ')'){
            toReturn = false;
        }
        return toReturn;
    }

    /**
     *
     * @param toConvert is string to convert to integer value
     * @return integer value
     */
    private int converterToNumber(String toConvert){
        int toReturn = 0;
        for (int i = 0; i < toConvert.length() - 1; i++) {
            toReturn = toReturn * 10 + (toConvert.charAt(i) - '0');
        }
        char lastSymbol = toConvert.charAt(toConvert.length() - 1);
        if (lastSymbol >= '0' && lastSymbol <= '9'){
            toReturn = toReturn * 10 + (lastSymbol - '0');
        }
        return toReturn;
    }

    /**
     * checks if string is action
     * @param toCheck is string to check
     * @return true if it is
     */
    private boolean isAction(String toCheck) {
        return toCheck.equals("+") || toCheck.equals("-") || toCheck.equals("*") || toCheck.equals("/");
    }

    /**
     * checks can string be identifier of tree nodes
     * @param toCheck is string to check
     * @return true if it is
     */
    private boolean hasAbilityToMakeTree(String toCheck){
        boolean toReturn = true;
        for (int i = 0; i < toCheck.length(); i++) {
            if (!isNumberActionOrBracket(toCheck.charAt(i))){
                toReturn = false;
            }
        }
        return toReturn;
    }

    /**
     * checks if symbol in string with tree element can be there
     * @param toCheck is string with tree element
     * @return true if it is
     */
    private boolean isNumberActionOrBracket(char toCheck) {
        return (toCheck >= '0' && toCheck <= '9') || toCheck == '+' || toCheck == '-'
                || toCheck == '*' || toCheck == '/' || toCheck == '(' || toCheck == ')';
    }
}
