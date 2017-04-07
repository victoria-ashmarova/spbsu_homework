public class Calculator {
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
