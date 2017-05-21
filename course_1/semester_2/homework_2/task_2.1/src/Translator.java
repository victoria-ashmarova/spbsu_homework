import java.util.Scanner;

/**
 * Class contains methods, which translate an expression in infix notation to an expression in postfix notation.
 */
public class Translator {
    /**
     * translates expression in infix form to expression in postfix form.
     * @param scan to get expression in infix form
     * @return string with expression in postfix form
     * @throws IncorrectExpressionException
     */
    public static String getPostFixExpression(Scanner scan) throws IncorrectExpressionException {
        String postfixExpression = "";
        Stack<String> buffer = new StackWithPointers<>();
        while (scan.hasNext()){
            String current = scan.next();
            if (isNumber(current)){
                postfixExpression = postfixExpression + " " + current;
            }
            if (current.equals("(")){
                buffer.push(current);
            }
            if (isFirstOperation(current)){
                addFirstOperation(buffer, postfixExpression, current);
            }
            if (isSecondOperation(current)){
                addSecondOperation(buffer, postfixExpression, current);
            }
            if (current.equals(")")){
                try {
                    String toAdd = buffer.pop();
                    while (!toAdd.equals("(")){
                        postfixExpression = postfixExpression + " " + toAdd;
                        toAdd = buffer.pop();
                    }
                } catch (EmptyStackException e){
                    throw new IncorrectExpressionException("There is no open bracket.");
                }
            }
        }
        while (buffer.getSize() > 0){
            try {
                postfixExpression = postfixExpression + " " + buffer.pop();
            } catch (EmptyStackException e) {};
        }
        return postfixExpression.substring(1);
    }

    /**
     * adds to expression in postfix form operations of multiplication and quotient.
     * @param buffer contains previous operations
     * @param postfixExpression is part of expression in postfix form
     * @param operation is current operation
     */
    private static void addFirstOperation(Stack<String> buffer, String postfixExpression, String operation){
        try{
            String prevOperation = buffer.pop();
            if (isFirstOperation(prevOperation)){
                while (buffer.getSize() >= 0 && isFirstOperation(prevOperation)){
                    postfixExpression = postfixExpression + " " + prevOperation;
                    prevOperation = buffer.pop();
                }
            }
            if (isSecondOperation(prevOperation) || prevOperation.equals("(")){
                buffer.push(prevOperation);
            }
            buffer.push(operation);
        } catch (EmptyStackException e){
            buffer.push(operation);
        }
    }

    /**
     * adds to expression in postfix form operations of sum and difference.
     * @param buffer contains previous operations
     * @param postfixExpression is part of expression in postfix form
     * @param operation is current operation
     */
    private static void addSecondOperation(Stack<String> buffer, String postfixExpression, String operation){
        try{
            String prevOperation = buffer.pop();
            while (buffer.getSize() >= 0 && !prevOperation.equals("(")){
                postfixExpression = postfixExpression + " " + prevOperation;
                prevOperation = buffer.pop();
            }
            if (prevOperation.equals("(")){
                buffer.push(prevOperation);
            }
            buffer.push(operation);
        } catch (EmptyStackException e){
            buffer.push(operation);
        }
    }

    /**
     * checks if string is integer number.
     * @param toCheck is string to check
     * @return true if it is
     */
    private static boolean isNumber(String toCheck){
        try{
            Integer.parseInt(toCheck);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * checks if object is multiplication("*") or quotient("/")
     * @param operation is value to check
     * @return  true if operation is multiplication("*") or quotient("/")
     */
    private static boolean isFirstOperation(String operation){
        return operation.equals("*") || operation.equals("/");
    }

    /**
     * checks if object is sum("+") or difference("-")
     * @param operation is value to check
     * @return  true if operation is sum("+") or difference("-")
     */
    private static boolean isSecondOperation(String operation){
        return operation.equals("+") || operation.equals("-");
    }
}
