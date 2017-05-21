import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        try {
            System.out.print("Enter expression in infix form.\n");
            Scanner scan = new Scanner(System.in);
            String postfixExpression = Translator.getPostFixExpression(scan);
            int value = Calculator.findValueOfExpressionInPostfixForm(postfixExpression);
            System.out.println("Value is " + value);
        } catch (IncorrectExpressionException e) {
            e.printStackTrace();
            e.message();
        }
    }
}
