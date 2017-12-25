package ashmarova.task_2_5_2.sample;

/**
 * Class contains method to calculate expression
 * and fields-helpers with last action and last value
 */
public class Calculator {
    private int currentValue = 0;
    private char currentAction = '\0';

    /**
     * sets action, which will me made next
     * @param currentAction is action to set
     */
    public void setCurrentAction(char currentAction) {
        this.currentAction = currentAction;
    }

    /**
     * clears calculator
     */
    public void clear() {
        currentValue = 0;
        currentAction = '\0';
    }

    /**
     * searches value of expression with integer value and calculator's data
     * @param newStringValue is string with new value
     * @return value of expression
     */
    public int calculate(String newStringValue) {
        int newIntValue = Integer.parseInt(newStringValue);
        switch (currentAction) {
            case '+': {
                currentValue += newIntValue;
                return currentValue;
            }
            case '-': {
                currentValue -= newIntValue;
                return currentValue;
            }
            case '*': {
                currentValue *= newIntValue;
                return currentValue;
            }
            case '/': {
                if (newIntValue == 0) {
                    throw new ArithmeticException();
                }
                currentValue /= newIntValue;
                return currentValue;
            }
            default: {
                currentValue = newIntValue;
                return currentValue;
            }
        }
    }
}
