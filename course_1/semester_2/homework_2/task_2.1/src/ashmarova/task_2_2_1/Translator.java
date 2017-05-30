package ashmarova.task_2_2_1;

import java.util.Scanner;

/**
 * Class contains methods, which translate an expression in infix notation to an expression in postfix notation.
 */
public class Translator {
    /**
     * translates expression in infix form to expression in postfix form.
     * @param scan to get expression in infix form
     * @return string with expression in postfix form
     * @throws IncorrectExpressionException when expression is not correct
     */
    public static String getPostFixExpression(Scanner scan) throws IncorrectExpressionException {
        Stack<String> buffer = new StackWithPointers<>();
        StringBuilder builder = new StringBuilder();
        while (scan.hasNext()) {
            String current = scan.next();
            if (isNumber(current)){
                builder.append(" ").append(current);
            }
            if (current.equals("(")){
                buffer.push(current);
            }
            if (isFirstOperation(current)){
                addFirstOperation(buffer, builder, current);
            }
            if (isSecondOperation(current)){
                addSecondOperation(buffer, builder, current);
            }
            if (current.equals(")")){
                addClosedBracket(buffer, builder);
            }
        }
        try{
            while (buffer.getSize() > 0){
                String toAdd = buffer.pop();
                if (toAdd.equals("(")) {
                    throw new IncorrectExpressionException("There is open bracket without closed bracket.");
                } else {
                    builder.append(" ").append(toAdd);
                }
            }
        } catch (EmptyStackException e) {}
        return builder.toString().substring(1);
    }

    /**
     * adds to expression in postfix form operation between closed and opened brackets.
     * @param buffer contains previous operations
     * @param builder collects part of expression in postfix form
     * @throws IncorrectExpressionException when there is no open bracket
     */
    private static void addClosedBracket(Stack<String> buffer, StringBuilder builder) throws IncorrectExpressionException {
        try {
            String toAdd = buffer.pop();
            while (!toAdd.equals("(")) {
                builder.append(" ").append(toAdd);
                toAdd = buffer.pop();
            }
        } catch (EmptyStackException e){
            throw new IncorrectExpressionException("There is closed bracket without open bracket.");
        }
    }

    /**
     * adds to expression in postfix form operations of multiplication and quotient.
     * @param buffer contains previous operations
     * @param builder collects part of expression in postfix form
     * @param operation is current operation
     */
    private static void addFirstOperation(Stack<String> buffer, StringBuilder builder, String operation){
        try{
            String prevOperation = buffer.pop();
            if (isFirstOperation(prevOperation)){
                while (buffer.getSize() >= 0 && isFirstOperation(prevOperation)){
                    builder.append(" ").append(prevOperation);
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
     * @param builder collects part of expression in postfix form
     * @param operation is current operation
     */
    private static void addSecondOperation(Stack<String> buffer, StringBuilder builder, String operation){
        try{
            String prevOperation = buffer.pop();
            while (buffer.getSize() >= 0 && !prevOperation.equals("(")){
                builder.append(" ").append(prevOperation);
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
