/**
 * Class contains methods to get a value of an expression in postfix form
 */
public class Calculator {
    /**
     * Gets from stack to top values and return result of operation to do
     * @param stack to get values for operation
     * @param currentValue is operation to do
     * @return value of operation
     * @throws IncorrectExpressionException when there is less values to calculate
     */
    private static int getValueOfOperation(Stack<Integer> stack, String currentValue) throws IncorrectExpressionException {
        try {
            int secondOperand = stack.pop();
            int firstOperand = stack.pop();
            switch (currentValue) {
                case "+": {
                    return firstOperand + secondOperand;
                }
                case "-": {
                    return firstOperand - secondOperand;
                }
                case "*": {
                    return firstOperand * secondOperand;
                }
                case "/": {
                    if (secondOperand == 0){
                        throw new ArithmeticException();
                    }
                    return firstOperand / secondOperand;
                }
            }
        } catch (EmptyStackException e){
            throw new IncorrectExpressionException("Less values.");
        }
        return 0;
    }

    /**
     * Find value of expression, which is in string.
     * The method uses stack of integer values to find value.
     * If current element of expression is number, it will be pushed to stack.
     * If current number of expression is operation, two values will be got from stack
     * and result of expression with these values and operation will be pushed to stack
     * @param expressionInPostfixForm is string with expression
     * @return value of expression
     * @throws IncorrectExpressionException when there is no ability to get value of some operation
     */
    public static int findValueOfExpressionInPostfixForm(String expressionInPostfixForm) throws IncorrectExpressionException {
        try {
            int toReturn = 0;
            String tokens[] = expressionInPostfixForm.split(" ");
            Stack<Integer> values = new StackWithPointers<>();
            for (int i = 0; i < tokens.length; i++) {
                try {
                    Integer toAdd = Integer.parseInt(tokens[i]);
                    values.push(toAdd);
                } catch (NumberFormatException e) {
                    if (isOperation(tokens[i])) {
                        values.push(getValueOfOperation(values, tokens[i]));
                    } else {
                        throw new IncorrectExpressionException("Data for calculation is not correct.");
                    }
                }
            }
            if (values.getSize() > 1){
                throw new IncorrectExpressionException("Too mush numbers");
            }
            return values.pop();
        } catch (EmptyStackException e) {
            throw new IncorrectExpressionException("Less values.");
        } catch (ArithmeticException e) {
            throw new IncorrectExpressionException("There is division by zero!");
        }
    }

    /**
     *
     * @param toCheck is string, which can be operation.
     * @return is string operation or not
     */
    private static boolean isOperation(String toCheck){
        return toCheck.equals("+") || toCheck.equals("-") || toCheck.equals("*") || toCheck.equals("/");
    }
}
