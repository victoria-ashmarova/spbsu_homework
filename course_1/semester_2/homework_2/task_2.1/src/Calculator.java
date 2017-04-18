public class Calculator {
    /**
     * Gets from stack to top values and return result of operation to do
     * if operation is not correct returns 0;
     * @param stack to get values for operation
     * @param currentValue is operation to do
     * @return value of operation
     * @throws ClassCastException
     * @throws EmptyStackException
     */
    protected static int getValueOfOperation(StackWithPointers stack, Object currentValue) throws ClassCastException, EmptyStackException {
        int secondOperand = (int)stack.pop();
        int firstOperand = (int)stack.pop();
        switch ((char)currentValue){
            case '+':{
                return firstOperand + secondOperand;
            }
            case '-':{
                return firstOperand - secondOperand;
            }
            case '*':{
                return firstOperand * secondOperand;
            }
            case '/':{
                return firstOperand / secondOperand;
            }
        }
        return 0;
    }

    /**
     * Find value of expression, which is in array of input data(parameter object)
     * The method uses stack to find value.
     * If current element of expression is number, it will be pushed to stack.
     * If current number of expression is operation, two values will be got from stack
     * and result of expression with these values and operation will be pushed to stack
     * @param expressionInPostfixForm is array with expression
     * @return value of expression
     * @throws EmptyStackException
     */
    public int findValueOfExpressionInPostfixForm(Object[] expressionInPostfixForm) throws EmptyStackException {
        int toReturn = 0;
        CheckingObjects checking = new CheckingObjects();
        StackWithPointers stack = new StackWithPointers();
        for (int i = 0; i < expressionInPostfixForm.length; i++){
            if (checking.isNumber(expressionInPostfixForm[i])){
                stack.push(expressionInPostfixForm[i]);
            }
            if (checking.isOperation(expressionInPostfixForm[i])){
                int newValue = getValueOfOperation(stack, expressionInPostfixForm[i]);
                stack.push(newValue);
            }
        }
        toReturn = (int)stack.pop();
        return toReturn;
    }
}
