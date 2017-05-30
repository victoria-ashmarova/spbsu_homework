package ashmarova.task_2_2_1;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        try {
            System.out.print("Enter expression in infix form: ");
            String postfixExpression = Translator.getPostFixExpression(new Scanner(System.in));
            int value = Calculator.findValueOfExpressionInPostfixForm(postfixExpression);
            System.out.println("Value is " + value);
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }
}
