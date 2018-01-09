package sample;

/**
 * Class, contains method, which return result of operation with two values.
 */
public class Calculator {
    /**
     * gets result of operation.
     * @param firstValue is first member of operation
     * @param secondValue is second member of operation
     * @param operation is operation to do
     * @return result of operation
     */
    public static double calculate(double firstValue, double secondValue, char operation){
        switch (operation){
            case '+':{
                return firstValue + secondValue;
            }
            case '-':{
                return firstValue - secondValue;
            }
            case '*':{
                return firstValue * secondValue;
            }
            case '/':{
                if (secondValue == 0){
                    throw new ArithmeticException();
                }
                return firstValue / secondValue;
            }
            default:{
                return 0;
            }
        }
    }
}
