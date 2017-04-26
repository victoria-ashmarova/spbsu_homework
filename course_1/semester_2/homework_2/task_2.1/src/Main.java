import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        System.out.print("Enter expression in infix form.\n");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        Reader reader = new Reader();
        Object expression[] = reader.toArray(line);
        TranslatorToPostfixNotation translator = new TranslatorToPostfixNotation();
        expression = translator.translateToPostfixNotation(expression);
        Calculator calcucator = new Calculator();
        try{
            int toReturn = calcucator.findValueOfExpressionInPostfixForm(expression);
            System.out.print("Result: " + toReturn);
        } catch (EmptyStackException e) {
            System.out.print("Cannot find value of expression.\n");
        };
    }
}
