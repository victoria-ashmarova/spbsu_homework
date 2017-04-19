/**
 * Class contains methods, which translate an expression in infix notation to an expression in postfix notation
 * Expressions are kept in arrays of Objects
 */
public class TranslatorToPostfixNotation {
    /**
     * Class for counting index of array with expression in postfix form
     */
    private class Counter{
        protected int index = 0;
    }

    /**
     * counts amount of brackets in expression
     * @param expression
     * @return amount of brackets
     */
    private int countBrackets(Object expression[]){
        int counter = 0;
        for (int i = 0; i < expression.length; i++){
            try{
                if ((char)expression[i] == '(' || (char)expression[i] == ')'){
                    counter++;
                }
            } catch (ClassCastException e){};
        }
        return counter;
    }

    /**
     * Make expression in posfix form with array-expression using method of sorting station
     * @param expression
     * @return expression in postfix form
     */
   public Object[] translateToPostfixNotation(Object expression[]) {
       StackWithPointers stack = new StackWithPointers();
       Object postfixExpression[] = new Object[expression.length - countBrackets(expression)];
       CheckingObjects checking = new CheckingObjects();
       Counter counter = new Counter();
       for (int i = 0; i < expression.length; i++){
           try{
               if (checking.isNumber(expression[i])){
                   postfixExpression[counter.index] = expression[i];
                   counter.index++;
               }

               if ((char) expression[i] == '('){
                   stack.push(expression[i]);
               }
               //TO DO
               if ((char) expression[i] == ')'){
                   try {
                       Object toAdd = stack.pop();
                       while ((char)toAdd != '('){
                           postfixExpression[counter.index] = toAdd;
                           counter.index++;
                           toAdd = stack.pop();
                       }
                   } catch (EmptyStackException e){}
               }
               if (checking.isFirstOperation(expression[i])){
                   addFirstOperation(stack, expression[i], postfixExpression, checking, counter);
               }

               if (checking.isSecondOperation(expression[i])){
                   addSecondOperation(stack, expression[i], postfixExpression, counter);
               }
           } catch (ClassCastException e) {};
       }

       while (stack.getSize() > 0){
           try {
               postfixExpression[counter.index] = stack.pop();
               counter.index++;
           } catch (EmptyStackException e){};
       }
       return postfixExpression;
   }

    /**
     * adds to expression in postfix form operations of composition and quotient
     * @param stack contains previous operations
     * @param operation contain current operation
     * @param postfixExpression contains part of expression in postfix form
     * @param checking checks type of operation
     * @param counter is index of current end of part of expression un postfix form
     */
   private void addFirstOperation(StackWithPointers stack, Object operation,
                                  Object[]postfixExpression, CheckingObjects checking, Counter counter){
       try{
           Object prevOperation = stack.pop();
           if (checking.isFirstOperation(prevOperation)){
               while (stack.getSize() >= 0 && checking.isFirstOperation(prevOperation)){
                   postfixExpression[counter.index] = prevOperation;
                   counter.index++;
                   prevOperation = stack.pop();
               }
           }
           if (checking.isSecondOperation(prevOperation) || (char)prevOperation == '('){
               stack.push(prevOperation);
           }
           stack.push(operation);
       } catch (EmptyStackException e){
           stack.push(operation);
       }
   }

    /**
     * adds to expression in postfix form operations of sum and difference
     * @param stack contains previous operations
     * @param operation contain current operation
     * @param postfixExpression contains part of expression in postfix form
     * @param counter is index of current end of part of expression un postfix form
     */
    private void addSecondOperation(StackWithPointers stack, Object operation,
                                    Object[]postfixExpression, Counter counter){
        try{
            Object prevOperation = stack.pop();
            while (stack.getSize() >= 0 && (char)prevOperation != '('){
                postfixExpression[counter.index] = prevOperation;
                counter.index++;
                prevOperation = stack.pop();
            }
            if ((char) prevOperation == '('){
                stack.push(prevOperation);
            }
            stack.push(operation);
        } catch (EmptyStackException e){
            stack.push(operation);
        }
    }
}
