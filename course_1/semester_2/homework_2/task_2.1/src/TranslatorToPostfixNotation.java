public class TranslatorToPostfixNotation {
   public Object[] translateToPostfixNotation(Object expression[]) {
       StackWithPointers stack = new StackWithPointers();
       Object postfixExpression[] = new Object[stack.getSize()];
       CheckingObjects checking = new CheckingObjects();
       int index = 0;
       for (int i = 0; i < expression.length; i++){
           if (checking.isNumber(expression[i])){
               postfixExpression[index] = expression[i];
               index++;
           }
           if ((char) expression[i] == '('){
               stack.push(expression[i]);
           }
           //TO DO
       }
       return postfixExpression;
   }
}
