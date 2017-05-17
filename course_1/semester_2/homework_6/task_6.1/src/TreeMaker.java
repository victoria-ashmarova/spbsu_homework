import java.util.Scanner;
import java.util.Stack;

public class TreeMaker {
    private AbstractNode root;
    private Scanner scan;

    TreeMaker(Scanner scan){
        this.scan = scan;
    }

    public int getValue() throws Exception {
        try {
            return root.getValue();
        } catch (NullPointerException e){
            System.out.print("Couldn't get value of tree\n");
            throw new Exception();
        }
    }

    public void print(){
        try {
            root.printAllChildren();
        } catch (NullPointerException e){
            System.out.print("Couldn't print tree\n");
        }
    }

    public void makeTree() throws lackOfValuesException {
        String toRead = null;
        Stack<Action> ownersOfEmptySons = new Stack<>();
        while (scan.hasNext()){
            toRead = scan.next();
            if (toRead.length() > 1 && toRead.charAt(0) == '('){
                toRead = toRead.substring(1);
            }
            if (isNumber(toRead)) {
                addNumberToTree(ownersOfEmptySons, converterToNumber(toRead));
            }
            if (isAction(toRead)){
                Action newAction = makeAction(toRead);
                addActionToTree(ownersOfEmptySons, newAction);
            }
        }
        scan.close();
        if (!ownersOfEmptySons.empty()) {
            throw new lackOfValuesException();
        }
    }

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

    private Action makeAction(String whatAction){
        Action newAction = null;
        switch (whatAction) {
            case "+": {
                newAction = new Sum();
                break;
            }
            case "-": {
                newAction = new Difference();
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

    private boolean isNumber(String toCheck){
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

    private boolean isAction(String toCheck) {
        return toCheck.equals("+") || toCheck.equals("-") || toCheck.equals("*") || toCheck.equals("/");
    }
}
